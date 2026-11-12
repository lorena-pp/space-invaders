package main;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class BoardTest {

    // ==============================================================================
    // 1. TESTS ESTÁNDAR (Ejecución única)
    // ==============================================================================
    public static class GameInitStandardTests {

        @Test
        public void creacionBoardTest() {
            Board board = new Board();
            assertNotNull("El tablero debe crearse correctamente", board);
        }

        @Test
        public void BVT_gameInit_jugadorYDisparo_inicializados() throws Exception {
            Board board = new Board();
            Method init = Board.class.getDeclaredMethod("gameInit");
            init.setAccessible(true);
            init.invoke(board);

            boolean inicializacionCorrecta = board.getPlayer() != null && board.getShot() != null;
            assertTrue("El jugador y el disparo deben estar inicializados tras gameInit", inicializacionCorrecta);
        }
    }

    // ==============================================================================
    // 2. TESTS PARAMETRIZADOS: gameInit() -> Posiciones de los Aliens (BVT)
    // ==============================================================================
    @RunWith(Parameterized.class)
    public static class GameInitAliensParameterizedTest {

        private int alienIndex;
        private int expectedX;
        private int expectedY;

        public GameInitAliensParameterizedTest(int alienIndex, int expectedX, int expectedY) {
            this.alienIndex = alienIndex;
            this.expectedX = expectedX;
            this.expectedY = expectedY;
        }

        @Parameterized.Parameters(name = "Alien pos {0} -> Esperado X:{1} Y:{2}")
        public static Collection<Object[]> data() {
            int ultimoIndice = (Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS) - 1;
            int maxCol = Commons.ALIEN_COLUMNS - 1;
            int maxRow = Commons.ALIEN_ROWS - 1;

            return Arrays.asList(new Object[][]{
                    {0, Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y},
                    {1, Commons.ALIEN_INIT_X + Commons.ALIEN_SEPARATOR, Commons.ALIEN_INIT_Y},
                    {ultimoIndice,
                            Commons.ALIEN_INIT_X + (Commons.ALIEN_SEPARATOR * maxCol),
                            Commons.ALIEN_INIT_Y + (Commons.ALIEN_SEPARATOR * maxRow)}
            });
        }

        @Test
        public void BVT_gameInit_posicionAliensParametrizada() throws Exception {
            Board board = new Board();
            Method init = Board.class.getDeclaredMethod("gameInit");
            init.setAccessible(true);
            init.invoke(board);

            List<Alien> aliens = board.getAliens();
            Alien targetAlien = aliens.get(alienIndex);

            assertEquals("Cálculo incorrecto en la posición X del alien", expectedX, targetAlien.getX());
            assertEquals("Cálculo incorrecto en la posición Y del alien", expectedY, targetAlien.getY());
        }
    }

    // ==============================================================================
    // 3. TESTS PARAMETRIZADOS: update() -> Condición de victoria (BVT)
    // ==============================================================================
    @RunWith(Parameterized.class)
    public static class UpdateVictoryParameterizedTest {

        private int offset;
        private boolean expectedInGame;
        private String expectedMessage;

        public UpdateVictoryParameterizedTest(int offset, boolean expectedInGame, String expectedMessage) {
            this.offset = offset;
            this.expectedInGame = expectedInGame;
            this.expectedMessage = expectedMessage;
        }

        @Parameterized.Parameters(name = "Offset muertes {0} -> Sigue jugando: {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {-10, true, ""},
                    {-1, true, ""},
                    {0, false, "Game won!"},
                    {1, false, "Game won!"}
            });
        }

        @Test
        public void BVT_update_condicionDeVictoria() throws Exception {
            Board board = new Board();

            Field inGameField = Board.class.getDeclaredField("inGame");
            inGameField.setAccessible(true);
            inGameField.set(board, true);

            Field messageField = Board.class.getDeclaredField("message");
            messageField.setAccessible(true);
            messageField.set(board, "");

            Field deathsField = Board.class.getDeclaredField("deaths");
            deathsField.setAccessible(true);
            int targetDeaths = Commons.NUMBER_OF_ALIENS_TO_DESTROY + offset;
            deathsField.set(board, Math.max(0, targetDeaths));

            Method updateMethod = Board.class.getDeclaredMethod("update");
            updateMethod.setAccessible(true);
            updateMethod.invoke(board);

            boolean actualInGame = (boolean) inGameField.get(board);
            String actualMessage = (String) messageField.get(board);

            assertEquals("La bandera inGame no es la esperada", expectedInGame, actualInGame);
            if (!expectedInGame) {
                assertEquals("El mensaje final no es correcto", expectedMessage, actualMessage);
            }
        }
    }

    // ==============================================================================
    // 4. TESTS PARAMETRIZADOS: update_aliens() -> Movimiento y bordes (BVT)
    // ==============================================================================
    @RunWith(Parameterized.class)
    public static class UpdateAliensParameterizedTest {

        private String testName;
        private int startX;
        private int startY;
        private int initialDirection;
        private int expectedDirection;
        private boolean expectedInGame;
        private String expectedMessage;
        private boolean expectDrop;

        public UpdateAliensParameterizedTest(String testName, int startX, int startY, int initialDirection,
                                             int expectedDirection, boolean expectDrop,
                                             boolean expectedInGame, String expectedMessage) {
            this.testName = testName;
            this.startX = startX;
            this.startY = startY;
            this.initialDirection = initialDirection;
            this.expectedDirection = expectedDirection;
            this.expectDrop = expectDrop;
            this.expectedInGame = expectedInGame;
            this.expectedMessage = expectedMessage;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> data() {
            int BORDER_LEFT = Commons.BORDER_LEFT;
            int BORDER_RIGHT = Commons.BORDER_RIGHT;
            int GROUND_LIMIT = Commons.GROUND - Commons.ALIEN_HEIGHT;

            return Arrays.asList(new Object[][]{
                    {"Nominal: Movimiento normal derecha", 100, 50, 1, 1, false, true, ""},
                    {"Borde Derecho: Invierte y baja", BORDER_RIGHT, 50, 1, -1, true, true, ""},
                    {"Borde Izquierdo: Invierte y baja", BORDER_LEFT, 50, -1, 1, true, true, ""},
                    {"Límite Inferior: Invasión", 100, GROUND_LIMIT, 1, 1, false, false, "Invasion!"}
            });
        }

        @Test
        public void BVT_updateAliens_movimientoYLimites() throws Exception {
            Board board = new Board();

            List<Alien> aliens = new ArrayList<>();
            Alien testAlien = new Alien(startX, startY);
            aliens.add(testAlien);

            Field aliensField = Board.class.getDeclaredField("aliens");
            aliensField.setAccessible(true);
            aliensField.set(board, aliens);

            Field directionField = Board.class.getDeclaredField("direction");
            directionField.setAccessible(true);
            directionField.set(board, initialDirection);

            Field inGameField = Board.class.getDeclaredField("inGame");
            inGameField.setAccessible(true);
            inGameField.set(board, true);

            Field messageField = Board.class.getDeclaredField("message");
            messageField.setAccessible(true);
            messageField.set(board, "");

            Method updateAliensMethod = Board.class.getDeclaredMethod("update_aliens");
            updateAliensMethod.setAccessible(true);
            updateAliensMethod.invoke(board);

            int actualDirection = (int) directionField.get(board);
            boolean actualInGame = (boolean) inGameField.get(board);
            String actualMessage = (String) messageField.get(board);

            assertEquals("La dirección de la formación no es la esperada", expectedDirection, actualDirection);
            assertEquals("La bandera inGame no es la correcta tras procesar el límite", expectedInGame, actualInGame);

            if (!expectedInGame) {
                assertEquals("El mensaje de invasión es incorrecto", expectedMessage, actualMessage);
            }

            if (expectDrop) {
                assertEquals("El alien debía bajar Commons.GO_DOWN al tocar el borde",
                        startY + Commons.GO_DOWN, testAlien.getY());
            }
        }
    }

    // ==============================================================================
    // 5. TESTS PARAMETRIZADOS: update_bomb() -> Movimiento y Colisiones (Decision Tables + BVT)
    // ==============================================================================
    @RunWith(Parameterized.class)
    public static class UpdateBombParameterizedTest {

        private String testName;
        private int bombStartX;
        private int bombStartY;
        private int playerX;
        private int playerY;
        private boolean expectedBombDestroyed;
        private boolean expectedPlayerDying;
        private boolean expectMoveDown;

        public UpdateBombParameterizedTest(String testName, int bombStartX, int bombStartY, int playerX, int playerY,
                                           boolean expectedBombDestroyed, boolean expectedPlayerDying, boolean expectMoveDown) {
            this.testName = testName;
            this.bombStartX = bombStartX;
            this.bombStartY = bombStartY;
            this.playerX = playerX;
            this.playerY = playerY;
            this.expectedBombDestroyed = expectedBombDestroyed;
            this.expectedPlayerDying = expectedPlayerDying;
            this.expectMoveDown = expectMoveDown;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> data() {
            int GROUND_LIMIT = Commons.GROUND - Commons.BOMB_HEIGHT;
            int P_X = 150;
            int P_Y = GROUND_LIMIT - 30;

            return Arrays.asList(new Object[][]{
                    // { testName, bombStartX, bombStartY, playerX, playerY, expectedBombDestroyed, expectedPlayerDying, expectMoveDown }

                    // REGLA 1 (Nominal): La bomba cae libremente sin chocar.
                    {"Regla 1: Bomba libre desciende", 50, P_Y - 50, P_X, P_Y, false, false, true},

                    // REGLA 2 (Borde Suelo): La bomba toca exactamente el límite inferior.
                    {"Regla 2: Bomba choca con el suelo", 50, GROUND_LIMIT, P_X, P_Y, true, false, false},

                    // REGLA 3 (Borde Player): La bomba solapa con la posición del jugador.
                    {"Regla 3: Bomba impacta al jugador", P_X, P_Y, P_X, P_Y, false, true, false}
            });
        }

        @Test
        public void testUpdateBombColisiones() throws Exception {
            Board board = new Board();

            // 1. Configurar Jugador
            Player player = new Player();
            player.setX(playerX);
            player.setY(playerY);
            player.setDying(false);

            Field playerField = Board.class.getDeclaredField("player");
            playerField.setAccessible(true);
            playerField.set(board, player);

            // 2. Configurar Alien y su Bomba interna
            Alien alien = new Alien(bombStartX, bombStartY);

            // Instanciación de la clase interna tal como indicaste
            Alien.Bomb bomb = alien.new Bomb(bombStartX, bombStartY);
            bomb.setDestroyed(false); // Nos aseguramos de que esté activa

            // Inyectamos la bomba manualmente en el alien para que update_bomb() la detecte
            Field bombField = Alien.class.getDeclaredField("bomb");
            bombField.setAccessible(true);
            bombField.set(alien, bomb);

            List<Alien> aliens = new ArrayList<>();
            aliens.add(alien);
            Field aliensField = Board.class.getDeclaredField("aliens");
            aliensField.setAccessible(true);
            aliensField.set(board, aliens);

            // 3. Ejecutar update_bomb()
            Method updateBombMethod = Board.class.getDeclaredMethod("update_bomb");
            updateBombMethod.setAccessible(true);
            updateBombMethod.invoke(board);

            // 4. Afirmaciones basadas en la Tabla de Decisión
            assertEquals("La condición isDying del jugador no coincide con la regla de colisión",
                    expectedPlayerDying, player.isDying());

            if (expectedBombDestroyed) {
                assertTrue("La bomba debería haberse destruido al tocar el suelo (isDestroyed = true)", bomb.isDestroyed());
            }

            if (expectMoveDown && !bomb.isDestroyed() && !player.isDying()) {
                assertEquals("La bomba no descendió la cantidad correcta (Commons.BOMB_SPEED)",
                        bombStartY + Commons.BOMB_SPEED, bomb.getY());
            }
        }
    }
    // ==============================================================================
    // 6. TESTS PARAMETRIZADOS: update_shots() -> Movimiento y Colisiones (Decision Tables + BVT)
    // ==============================================================================
    @RunWith(Parameterized.class)
    public static class UpdateShotsParameterizedTest {

        private String testName;
        private int shotStartX;
        private int shotStartY;
        private int alienX;
        private int alienY;
        private boolean expectedShotDying;
        private boolean expectedAlienDying;
        private boolean expectDeathsIncrement;
        private boolean expectMoveUp;

        public UpdateShotsParameterizedTest(String testName, int shotStartX, int shotStartY, int alienX, int alienY,
                                            boolean expectedShotDying, boolean expectedAlienDying,
                                            boolean expectDeathsIncrement, boolean expectMoveUp) {
            this.testName = testName;
            this.shotStartX = shotStartX;
            this.shotStartY = shotStartY;
            this.alienX = alienX;
            this.alienY = alienY;
            this.expectedShotDying = expectedShotDying;
            this.expectedAlienDying = expectedAlienDying;
            this.expectDeathsIncrement = expectDeathsIncrement;
            this.expectMoveUp = expectMoveUp;
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<Object[]> data() {
            int TOP_LIMIT = 0; // Límite superior típico de la pantalla
            int A_X = 150;
            int A_Y = 100;

            return Arrays.asList(new Object[][] {
                    // { testName, shotStartX, shotStartY, alienX, alienY, expectedShotDying, expectedAlienDying, expectDeathsIncrement, expectMoveUp }

                    // REGLA 1 (Nominal): El disparo sube libremente, lejos de los límites y del alien.
                    { "Regla 1: Disparo libre asciende", A_X + 50, A_Y + 50, A_X, A_Y, false, false, false, true },

                    // REGLA 2 (Borde Superior): El disparo alcanza el límite superior de la pantalla.
                    { "Regla 2: Disparo sale del tablero", A_X, TOP_LIMIT, A_X, A_Y, true, false, false, false },

                    // REGLA 3 (Borde Alien): El disparo colisiona con las coordenadas del alien.
                    { "Regla 3: Disparo impacta al alien", A_X, A_Y, A_X, A_Y, true, true, true, false }
            });
        }

        @Test
        public void testUpdateShotsColisiones() throws Exception {
            Board board = new Board();

            // 1. Configurar Alien
            Alien alien = new Alien(alienX, alienY);
            alien.setDying(false); // Alien vivo

            List<Alien> aliens = new ArrayList<>();
            aliens.add(alien);
            Field aliensField = Board.class.getDeclaredField("aliens");
            aliensField.setAccessible(true);
            aliensField.set(board, aliens);

            // 2. Configurar Disparo (Shot) usando métodos heredados de Sprite
            // Si tu clase Shot no tiene este constructor, cámbialo a: Shot shot = new Shot(); shot.setX(...);
            space_invaders.sprites.Shot shot = new space_invaders.sprites.Shot(shotStartX, shotStartY);
            shot.setDying(false); // Disparo activo

            Field shotField = Board.class.getDeclaredField("shot");
            shotField.setAccessible(true);
            shotField.set(board, shot);

            // 3. Configurar Muertes iniciales (Deaths)
            Field deathsField = Board.class.getDeclaredField("deaths");
            deathsField.setAccessible(true);
            deathsField.set(board, 0); // Muertes iniciales = 0

            // Forzamos inGame a true para que las validaciones pasen fluidas
            Field inGameField = Board.class.getDeclaredField("inGame");
            inGameField.setAccessible(true);
            inGameField.set(board, true);

            // 4. Ejecutar update_shots()
            Method updateShotsMethod = Board.class.getDeclaredMethod("update_shots");
            updateShotsMethod.setAccessible(true);
            updateShotsMethod.invoke(board);

            // 5. Afirmaciones basadas en la Tabla de Decisión
            int actualDeaths = (int) deathsField.get(board);

            // Validar Alien
            assertEquals("El estado isDying del alien no coincide con la regla de colisión",
                    expectedAlienDying, alien.isDying());

            // Validar Disparo
            assertEquals("El estado isDying del disparo no coincide con la regla",
                    expectedShotDying, shot.isDying());

            // Validar Incremento de Muertes
            if (expectDeathsIncrement) {
                assertEquals("El contador de muertes (deaths) no se incrementó tras el impacto", 1, actualDeaths);
            } else {
                assertEquals("El contador de muertes no debía alterarse", 0, actualDeaths);
            }

            // Validar Movimiento hacia arriba
            if (expectMoveUp && !shot.isDying()) {
                // Sube disminuyendo la coordenada Y
                assertEquals("El disparo no ascendió la cantidad correcta (Commons.SHOT_SPEED)",
                        shotStartY - Commons.SHOT_SPEED, shot.getY());
            }
        }
    }
}