package main;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    // ==============================================================================
    // 1. TESTS ESTÁNDAR (Ejecución única)
    // ==============================================================================
    @Nested
    public class GameInitStandardTests {

        @Test
        public void creacionBoardTest() {
            Board board = new Board();
            assertNotNull(board, "El tablero debe crearse correctamente");
        }

        @Test
        public void BVT_gameInit_jugadorYDisparo_inicializados() throws Exception {
            Board board = new Board();
            Method init = Board.class.getDeclaredMethod("gameInit");
            init.setAccessible(true);
            init.invoke(board);

            boolean inicializacionCorrecta = board.getPlayer() != null && board.getShot() != null;
            assertTrue(inicializacionCorrecta, "El jugador y el disparo deben estar inicializados tras gameInit");
        }
    }

    // ==============================================================================
    // 2. TESTS PARAMETRIZADOS: gameInit() -> Posiciones de los Aliens (BVT)
    // ==============================================================================
    @Nested
    public class GameInitAliensParameterizedTest {

        public static Stream<Arguments> data() {
            int ultimoIndice = (Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS) - 1;
            int maxCol = Commons.ALIEN_COLUMNS - 1;
            int maxRow = Commons.ALIEN_ROWS - 1;

            return Stream.of(
                    Arguments.of(0, Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y),
                    Arguments.of(1, Commons.ALIEN_INIT_X + Commons.ALIEN_SEPARATOR, Commons.ALIEN_INIT_Y),
                    Arguments.of(ultimoIndice,
                            Commons.ALIEN_INIT_X + (Commons.ALIEN_SEPARATOR * maxCol),
                            Commons.ALIEN_INIT_Y + (Commons.ALIEN_SEPARATOR * maxRow))
            );
        }

        @ParameterizedTest(name = "Alien pos {0} -> Esperado X:{1} Y:{2}")
        @MethodSource("data")
        public void BVT_gameInit_posicionAliensParametrizada(int alienIndex, int expectedX, int expectedY) throws Exception {
            Board board = new Board();
            Method init = Board.class.getDeclaredMethod("gameInit");
            init.setAccessible(true);
            init.invoke(board);

            List<Alien> aliens = board.getAliens();
            Alien targetAlien = aliens.get(alienIndex);

            assertEquals(expectedX, targetAlien.getX(), "Cálculo incorrecto en la posición X del alien");
            assertEquals(expectedY, targetAlien.getY(), "Cálculo incorrecto en la posición Y del alien");
        }
    }

    // ==============================================================================
// 3. TESTS PARAMETRIZADOS: update() -> Condición de victoria (BVT)
// ==============================================================================
    @Nested
    public class UpdateVictoryParameterizedTest {

        public static Stream<Arguments> data() {
            return Stream.of(
                    Arguments.of(-10, true, ""),
                    Arguments.of(-1, true, ""),
                    Arguments.of(0, false, "Game won!"),
                    Arguments.of(1, false, "Game won!")
            );
        }

        @ParameterizedTest(name = "Offset muertes {0} -> Sigue jugando: {1}")
        @MethodSource("data")
        public void BVT_update_condicionDeVictoria(int offset, boolean expectedInGame, String expectedMessage) throws Exception {
            Board board = new Board();

            board.setInGame(true);
            board.setMessage("");

            int targetDeaths = Commons.NUMBER_OF_ALIENS_TO_DESTROY + offset;
            board.setDeaths(Math.max(0, targetDeaths));

            Method updateMethod = Board.class.getDeclaredMethod("update");
            updateMethod.setAccessible(true);
            updateMethod.invoke(board);

            boolean actualInGame = board.isInGame();
            String actualMessage = board.getMessage();

            assertEquals(expectedInGame, actualInGame, "La bandera inGame no es la esperada");
            if (!expectedInGame) {
                assertEquals(expectedMessage, actualMessage, "El mensaje final no es correcto");
            }
        }
    }

    // ==============================================================================
// 4. TESTS PARAMETRIZADOS: update_aliens() -> Movimiento y bordes (BVT)
// ==============================================================================
    @Nested
    public class UpdateAliensParameterizedTest {

        public static Stream<Arguments> data() {
            int BORDER_LEFT = Commons.BORDER_LEFT;
            int BORDER_RIGHT = Commons.BORDER_RIGHT;
            int GROUND_LIMIT = Commons.GROUND - Commons.ALIEN_HEIGHT;

            return Stream.of(
                    Arguments.of("Nominal: Movimiento normal derecha", 100, 50, 1, 1, false, true, ""),
                    Arguments.of("Borde Derecho: Invierte y baja", BORDER_RIGHT, 50, 1, -1, true, true, ""),
                    Arguments.of("Borde Izquierdo: Invierte y baja", BORDER_LEFT, 50, -1, 1, true, true, ""),
                    Arguments.of("Límite Inferior: Invasión", 100, GROUND_LIMIT, 1, 1, false, false, "Invasion!")
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("data")
        public void BVT_updateAliens_movimientoYLimites(String testName, int startX, int startY, int initialDirection,
                                                        int expectedDirection, boolean expectDrop,
                                                        boolean expectedInGame, String expectedMessage) throws Exception {
            Board board = new Board();

            List<Alien> aliens = new ArrayList<>();
            Alien testAlien = new Alien(startX, startY);
            aliens.add(testAlien);

            board.setAliens(aliens);
            board.setDirection(initialDirection);
            board.setInGame(true);
            board.setMessage("");

            Method updateAliensMethod = Board.class.getDeclaredMethod("update_aliens");
            updateAliensMethod.setAccessible(true);
            updateAliensMethod.invoke(board);

            int actualDirection = board.getDirection();
            boolean actualInGame = board.isInGame();
            String actualMessage = board.getMessage();

            assertEquals(expectedDirection, actualDirection, "La dirección de la formación no es la esperada");
            assertEquals(expectedInGame, actualInGame, "La bandera inGame no es la correcta tras procesar el límite");

            if (!expectedInGame) {
                assertEquals(expectedMessage, actualMessage, "El mensaje de invasión es incorrecto");
            }

            if (expectDrop) {
                assertEquals(startY + Commons.GO_DOWN, testAlien.getY(),
                        "El alien debía bajar Commons.GO_DOWN al tocar el borde");
            }
        }
    }

    // ==============================================================================
// 5. TESTS PARAMETRIZADOS: update_bomb() -> Movimiento y Colisiones (Decision Tables + BVT)
// ==============================================================================
    @Nested
    public class UpdateBombParameterizedTest {

        public static Stream<Arguments> data() {
            int GROUND_LIMIT = Commons.GROUND - Commons.BOMB_HEIGHT;
            int P_X = 150;
            int P_Y = GROUND_LIMIT - 30;

            return Stream.of(
                    // REGLA 1 (Nominal): La bomba cae libremente sin chocar.
                    Arguments.of("Regla 1: Bomba libre desciende", 50, P_Y - 50, P_X, P_Y, false, false, true),

                    // REGLA 2 (Borde Suelo): La bomba toca exactamente el límite inferior.
                    Arguments.of("Regla 2: Bomba choca con el suelo", 50, GROUND_LIMIT, P_X, P_Y, true, false, false),

                    // REGLA 3 (Borde Player): La bomba solapa con la posición del jugador.
                    Arguments.of("Regla 3: Bomba impacta al jugador", P_X, P_Y, P_X, P_Y, false, true, false)
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("data")
        public void testUpdateBombColisiones(String testName, int bombStartX, int bombStartY, int playerX, int playerY,
                                             boolean expectedBombDestroyed, boolean expectedPlayerDying, boolean expectMoveDown) throws Exception {
            Board board = new Board();

            // 1. Configurar Jugador
            Player player = new Player();
            player.setX(playerX);
            player.setY(playerY);
            player.setDying(false);
            board.setPlayer(player);

            // 2. Configurar Alien y su Bomba interna
            Alien alien = new Alien(bombStartX, bombStartY);
            Alien.Bomb bomb = alien.getBomb();
            bomb.setX(bombStartX);
            bomb.setY(bombStartY);
            bomb.setDestroyed(false);

            List<Alien> aliens = new ArrayList<>();
            aliens.add(alien);
            board.setAliens(aliens);

            // 3. Ejecutar update_bomb()
            Method updateBombMethod = Board.class.getDeclaredMethod("update_bomb");
            updateBombMethod.setAccessible(true);
            updateBombMethod.invoke(board);

            // 4. Afirmaciones basadas en la Tabla de Decisión
            assertEquals(expectedPlayerDying, player.isDying(),
                    "La condición isDying del jugador no coincide con la regla de colisión");

            if (expectedBombDestroyed) {
                assertTrue(bomb.isDestroyed(), "La bomba debería haberse destruido al tocar el suelo (isDestroyed = true)");
            }

            if (expectMoveDown && !bomb.isDestroyed() && !player.isDying()) {
                assertEquals(bombStartY + Commons.BOMB_SPEED, bomb.getY(),
                        "La bomba no descendió la cantidad correcta (Commons.BOMB_SPEED)");
            }
        }
    }

    // ==============================================================================
// 6. TESTS PARAMETRIZADOS: update_shots() -> Movimiento y Colisiones (Decision Tables + BVT)
// ==============================================================================
    @Nested
    public class UpdateShotsParameterizedTest {

        public static Stream<Arguments> data() {
            int TOP_LIMIT = 0;
            int A_X = 150;
            int A_Y = 100;

            return Stream.of(
                    // REGLA 1 (Nominal): El disparo sube libremente, lejos de los límites y del alien.
                    Arguments.of("Regla 1: Disparo libre asciende", A_X + 50, A_Y + 50, A_X, A_Y, false, false, false, true),

                    // REGLA 2 (Borde Superior): El disparo alcanza el límite superior de la pantalla.
                    Arguments.of("Regla 2: Disparo sale del tablero", A_X, TOP_LIMIT, A_X, A_Y, true, false, false, false),

                    // REGLA 3 (Borde Alien): El disparo colisiona con las coordenadas del alien.
                    Arguments.of("Regla 3: Disparo impacta al alien", A_X, A_Y, A_X, A_Y, true, true, true, false)
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("data")
        public void testUpdateShotsColisiones(String testName, int shotStartX, int shotStartY, int alienX, int alienY,
                                              boolean expectedShotDying, boolean expectedAlienDying,
                                              boolean expectDeathsIncrement, boolean expectMoveUp) throws Exception {
            Board board = new Board();

            // 1. Configurar Alien
            Alien alien = new Alien(alienX, alienY);
            alien.setDying(false);

            List<Alien> aliens = new ArrayList<>();
            aliens.add(alien);
            board.setAliens(aliens);

            // 2. Configurar Disparo (Shot)
            space_invaders.sprites.Shot shot = new space_invaders.sprites.Shot(shotStartX, shotStartY);
            shot.setDying(false);
            board.setShot(shot);

            // 3. Configurar Muertes iniciales (Deaths)
            board.setDeaths(0);
            board.setInGame(true);

            // 4. Ejecutar update_shots()
            Method updateShotsMethod = Board.class.getDeclaredMethod("update_shots");
            updateShotsMethod.setAccessible(true);
            updateShotsMethod.invoke(board);

            // 5. Afirmaciones basadas en la Tabla de Decisión
            int actualDeaths = board.getDeaths();

            assertEquals(expectedAlienDying, alien.isDying(),
                    "El estado isDying del alien no coincide con la regla de colisión");

            assertEquals(expectedShotDying, shot.isDying(),
                    "El estado isDying del disparo no coincide con la regla");

            if (expectDeathsIncrement) {
                assertEquals(1, actualDeaths, "El contador de muertes (deaths) no se incrementó tras el impacto");
            } else {
                assertEquals(0, actualDeaths, "El contador de muertes no debía alterarse");
            }

            if (expectMoveUp && !shot.isDying()) {
                assertEquals(shotStartY - Commons.SHOT_SPEED, shot.getY(),
                        "El disparo no ascendió la cantidad correcta (Commons.SHOT_SPEED)");
            }
        }
    }
}