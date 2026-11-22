package space_invaders.sprites;

import main.Board;
import main.Commons;
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

    static Stream<Arguments> actMovementProvider() {
        return Stream.of(
                Arguments.of(X_CENTRO, MOV_IZQUIERDA, X_CENTRO + MOV_IZQUIERDA, "Player should be moved to the left"),
                Arguments.of(X_CENTRO, SIN_MOV, X_CENTRO + SIN_MOV, "Player should not be moved"),
                Arguments.of(X_CENTRO, MOV_DERECHA, X_CENTRO + MOV_DERECHA, "Player should move to the right"),

                Arguments.of(AlienTest.X_MIN, MOV_IZQUIERDA, AlienTest.X_MIN, "Player should stay in the left corner"),
                Arguments.of(AlienTest.X_MAX, MOV_DERECHA, AlienTest.X_MAX, "Player should stay in the right corner")
        );
    }

    @Test
    void CE_creacionPlayerPosicionCorrecta_valoresNominales() {
        int valorY = Commons.GROUND - 10; // el enunciado dice que está 10 píxeles por encima del ground

        Player player = new Player();
        boolean posicionCorrecta = player.getX() == X_CENTRO && player.getY() == valorY;

        assertTrue(posicionCorrecta, "Player should be in the correct position");
    }

    @ParameterizedTest
    @MethodSource("actMovementProvider")
    void TD_act_movimientoYLimites(int valorX, int valorDX, int expectedX, String mensaje) {
        Player player = new Player();
        player.setX(valorX);
        player.dx = valorDX;

        player.act();

        boolean posicionActualizada = player.getX() == expectedX;

        assertTrue(posicionActualizada, mensaje);
    }

    @Test
    void BVT_keyPressedDerecha_valoresNominales() {
        int valorInicX = Commons.BOARD_WIDTH / 2;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        board.getPlayer().keyPressed(keyEvent);
        board.getPlayer().act();

        boolean seMueveBien = valorInicX + 2 == board.getPlayer().getX();
        assertTrue(seMueveBien, "Player se ha movido a la derecha en 1 unidad");
    }

    @Test
    void BVT_keyPressedIzquierda_valoresNominales() { //HAY QUE CAMBIAR EL TEST PARA QUE USE EL PLAYER DE BOARD
        int valorInicX = Commons.BOARD_WIDTH / 2;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

        board.getPlayer().keyPressed(keyEvent);
        board.getPlayer().act();

        boolean seMueveBien = valorInicX - 2 == board.getPlayer().getX();
        assertTrue(seMueveBien, "Player se ha movido a la izquierda en 1 unidad");
    }

    @Test
    void BVT_keyPressedDerecha_ExtremoDerecho_PixelesPares() {
        int valorInicX = Commons.BOARD_WIDTH - Commons.PLAYER_WIDTH;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        board.getPlayer().setX(valorInicX);
        board.getPlayer().keyPressed(keyEvent);
        board.getPlayer().act();

        boolean noSeMueve = valorInicX == board.getPlayer().getX();
        assertTrue(noSeMueve, "Player no se ha movido a la derecha");
    }

    @Test
    void BVT_keyPressedIzquierda_ExtremoIzquierdo_PixelesPares() {
        int valorInicX = 0;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

        board.getPlayer().setX(valorInicX);
        board.getPlayer().keyPressed(keyEvent);
        board.getPlayer().act();

        boolean noSeMueve = valorInicX == board.getPlayer().getX();
        assertTrue(noSeMueve, "Player no se ha movido a la izquierda");
    }

    @Test
    void BVT_keyPressedDerecha_ExtremoDerecho_PixelesImpares() {
        int valorInicX = Commons.BOARD_WIDTH - Commons.PLAYER_WIDTH - 1;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        board.getPlayer().setX(valorInicX);
        board.getPlayer().keyPressed(keyEvent);
        board.getPlayer().act();

        boolean noSeMueve = valorInicX == board.getPlayer().getX();
        assertTrue(noSeMueve, "Player no se ha movido a la derecha");
    }

    @Test
    void BVT_keyPressedIzquierda_ExtremoIzquierdo_PixelesImpares() {
        int valorInicX = 1;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

        board.getPlayer().setX(valorInicX);
        board.getPlayer().keyPressed(keyEvent);
        board.getPlayer().act();

        boolean noSeMueve = valorInicX == board.getPlayer().getX();
        assertTrue(noSeMueve, "Player no se ha movido a la izquierda");
    }


    @Test
    void CE_keyReleasedDerecha_valoresNominales() {
        int valorInicX = Commons.BOARD_WIDTH / 2;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        board.getPlayer().keyReleased(keyEvent);
        board.getPlayer().act();

        boolean respuesta = valorInicX == board.getPlayer().getX();
        assertTrue(respuesta, "Release correcto");
    }

    @Test
    void CE_keyReleasedIzquierda_valoresNominales() {
        int valorInicX = Commons.BOARD_WIDTH / 2;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

        board.getPlayer().keyReleased(keyEvent);
        board.getPlayer().act();

        boolean respuesta = valorInicX == board.getPlayer().getX();
        assertTrue(respuesta, "Release correcto");
    }
}