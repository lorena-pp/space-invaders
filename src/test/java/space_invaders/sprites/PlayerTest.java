package space_invaders.sprites;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    @Test
    void CE_creacionPlayerPosicionCorrecta_valoresNominales() {
        int valorX = Commons.BOARD_WIDTH/2;
        int valorY = Commons.GROUND-10; //el enunciado dice que está 10 píxeles por encima del ground

        Player player = new Player();
        boolean posicionCorrecta = player.getX() == valorX && player.getY() == valorY;

        assertTrue(posicionCorrecta, "Player should be in the correct position");
    }



    @Test
    void BVT_keyPressedDerecha_valoresNominales() {
        int valorInicX = Commons.BOARD_WIDTH/2;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        Player player = new Player();
        player.keyPressed(keyEvent);
        player.act();

        boolean seMueveBien = valorInicX+2 == player.getX();
        assertTrue(seMueveBien, "Player se ha movido a la derecha en 1 unidad");
    }

    @Test
    void BVT_keyPressedIzquierda_valoresNominales() {
        int valorInicX = Commons.BOARD_WIDTH/2;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

        Player player = new Player();
        player.keyPressed(keyEvent);
        player.act();

        boolean seMueveBien = valorInicX-2 == player.getX();
        assertTrue(seMueveBien, "Player se ha movido a la izquierda en 1 unidad");
    }

    @Test
    void BVT_keyPressedDerecha_ExtremoDerecho_PixelesPares() {
        int valorInicX = Commons.BOARD_WIDTH - Commons.PLAYER_WIDTH;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        Player player = new Player();
        player.setX(valorInicX);
        player.keyPressed(keyEvent);
        player.act();

        boolean noSeMueve = valorInicX == player.getX();
        assertTrue(noSeMueve, "Player no se ha movido a la derecha");
    }

    @Test
    void BVT_keyPressedIzquierda_ExtremoIzquierdo_PixelesPares() {
        int valorInicX = 0;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

        Player player = new Player();
        player.setX(valorInicX);
        player.keyPressed(keyEvent);
        player.act();

        boolean noSeMueve = valorInicX == player.getX();
        assertTrue(noSeMueve, "Player no se ha movido a la izquierda");
    }

    @Test
    void BVT_keyPressedDerecha_ExtremoDerecho_PixelesImpares() {
        int valorInicX = Commons.BOARD_WIDTH - Commons.PLAYER_WIDTH - 1;
        //el menos -1 se añade para que, en caso de que la nave se mueva en pixeles impares, no se salga de los límites
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        Player player = new Player();
        player.setX(valorInicX);
        player.keyPressed(keyEvent);
        player.act();

        boolean noSeMueve = valorInicX == player.getX();
        assertTrue(noSeMueve, "Player no se ha movido a la derecha");
    }

    @Test
    void BVT_keyPressedIzquierda_ExtremoIzquierdo_PixelesImpares() {
        int valorInicX = 1;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

        Player player = new Player();
        player.setX(valorInicX);
        player.keyPressed(keyEvent);
        player.act();

        boolean noSeMueve = valorInicX == player.getX();
        assertTrue(noSeMueve, "Player no se ha movido a la izquierda");
    }



    @Test
    void CE_keyReleasedDerecha_valoresNominales() {
        int valorInicX = Commons.BOARD_WIDTH/2;
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

        Player player = new Player();
        player.keyReleased(keyEvent);
        player.act();

        boolean respuesta = valorInicX == player.getX();
        assertTrue(respuesta, "Release correcto");
    }

    @Test
    void CE_keyReleasedIzquierda_valoresNominales() {
        int valorInicX = Commons.BOARD_WIDTH/2;
        Player player = new Player();
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);


        player.keyReleased(keyEvent);
        player.act();

        boolean respuesta = valorInicX == player.getX();
        assertTrue(respuesta, "Release correcto");
    }
}