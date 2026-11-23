package space_invaders.sprites;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerBlackTest {

    public static final int MOV_IZQUIERDA = -2;
    public static final int MOV_DERECHA = 2;
    public static final int SIN_MOV = 0;

    public static final int X_CENTRO = Commons.BOARD_WIDTH / 2;

    @Nested
    public class PlayerStandardTests {

        @Test
        @DisplayName(value = "CN-32")
        public void CE_creacionPlayer_PosicionCorrecta_valoresNominales() {
            int valorY = Commons.GROUND - 10;

            Player player = new Player();
            boolean posicionCorrecta = player.getX() == X_CENTRO && player.getY() == valorY;

            assertTrue(posicionCorrecta, "Player should be in the correct position");
        }
    }

    @Nested
    public class PlayerActParameterizedTest {

        public static Stream<Arguments> data() {
            return Stream.of(

                    // Clases válidas
                    Arguments.of("CN-33", X_CENTRO, SIN_MOV, X_CENTRO + SIN_MOV, "Player should not be moved"),
                    Arguments.of("CN-34", X_CENTRO, MOV_IZQUIERDA, X_CENTRO + MOV_IZQUIERDA, "Player should be moved to the left"),
                    Arguments.of("CN-35", X_CENTRO, MOV_DERECHA, X_CENTRO + MOV_DERECHA, "Player should move to the right"),

                    // Clases inválidas
                    Arguments.of("CN-36", AlienBlackTest.X_MIN, MOV_IZQUIERDA, AlienBlackTest.X_MIN, "Player should stay in the left corner"),
                    Arguments.of("CN-37", AlienBlackTest.X_MAX, MOV_DERECHA, AlienBlackTest.X_MAX, "Player should stay in the right corner")
            );
        }

        @ParameterizedTest(name = " {0}: act() X:{1} DX:{2} -> Esperado X:{3}")
        @MethodSource("data")
        public void TD_act_movimientoYLimites(String id, int valorX, int valorDX, int expectedX, String mensaje) {
            Player player = new Player();
            player.setX(valorX);
            player.dx = valorDX;

            player.act();

            boolean posicionActualizada = player.getX() == expectedX;

            assertTrue(posicionActualizada, mensaje);
        }
    }

    @Nested
    public class PlayerKeyPressedParameterizedTest {

        public static Stream<Arguments> data() {
            int xCentro = Commons.BOARD_WIDTH / 2;
            int xExtremoDerPar = Commons.BOARD_WIDTH - Commons.PLAYER_WIDTH;
            int xExtremoDerImpar = Commons.BOARD_WIDTH - Commons.PLAYER_WIDTH - 1;

            return Stream.of(
                    Arguments.of("CN-38", xCentro, KeyEvent.VK_RIGHT, xCentro + 2, "Player se ha movido a la derecha en 1 unidad"),
                    Arguments.of("CN-39", xCentro, KeyEvent.VK_LEFT, xCentro - 2, "Player se ha movido a la izquierda en 1 unidad"),
                    Arguments.of("CN-40", xExtremoDerPar, KeyEvent.VK_RIGHT, xExtremoDerPar, "Player no se ha movido a la derecha"),
                    Arguments.of("CN-41", 0, KeyEvent.VK_LEFT, 0, "Player no se ha movido a la izquierda"),
                    Arguments.of("CN-42", xExtremoDerImpar, KeyEvent.VK_RIGHT, xExtremoDerImpar, "Player no se ha movido a la derecha"),
                    Arguments.of("CN-43", 1, KeyEvent.VK_LEFT, 1, "Player no se ha movido a la izquierda")
            );
        }

        @ParameterizedTest(name = "{0}: keyPressed X:{1} Tecla:{2} -> Esperado X:{3}")
        @MethodSource("data")
        public void TD_keyPressed_Parametrizado(String id, int valorInicX, int keyCode, int expectedX, String mensaje) {
            Board board = new Board();
            KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                    0, keyCode, KeyEvent.CHAR_UNDEFINED);

            board.getPlayer().setX(valorInicX);
            board.getPlayer().keyPressed(keyEvent);
            board.getPlayer().act();

            boolean resultadoCorrecto = expectedX == board.getPlayer().getX();
            assertTrue(resultadoCorrecto, mensaje);
        }
    }

    @Nested
    public class PlayerKeyReleasedParameterizedTest {

        public static Stream<Arguments> data() {
            return Stream.of(
                    Arguments.of("CN-44", KeyEvent.VK_RIGHT),
                    Arguments.of("CN-45", KeyEvent.VK_LEFT)
            );
        }

        @ParameterizedTest(name = "{0}: keyReleased Tecla:{1}")
        @MethodSource("data")
        public void CE_keyReleased_valoresNominales_Parametrizado(String id, int keyCode) {
            int valorInicX = Commons.BOARD_WIDTH / 2;
            Board board = new Board();
            KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                    0, keyCode, KeyEvent.CHAR_UNDEFINED);

            board.getPlayer().keyReleased(keyEvent);
            board.getPlayer().act();

            boolean respuesta = valorInicX == board.getPlayer().getX();
            assertTrue(respuesta, "Release correcto");
        }
    }
}