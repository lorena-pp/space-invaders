package space_invaders.sprites;

import main.Board;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerWhiteTest {

    @Test
    void keypressedTestCaminoA(){ //NO SE HA HECHO NINGÚN CASO DE USO PARA EL NODO 2
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyPressed(keyEvent);
        assertEquals(0, board.getPlayer().dx,"DX debería ser 0 para no moverse");
    }

    @Test
    void keypressedTestCaminoB(){
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyPressed(keyEvent);
        assertEquals(2, board.getPlayer().dx,"DX debería ser 2 para irse a la derecha");
    }

    @Test
    void keypressedTestCaminoC(){
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyPressed(keyEvent);
        assertEquals(-2, board.getPlayer().dx,"DX debería ser -2 para irse a la izquierda");
    }

    @Test
    void keyreleasedTestCaminoA(){
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyReleased(keyEvent);
        assertEquals(0, board.getPlayer().dx,"DX debería ser 0 para no moverse");
    }

    @Test
    void keyreleasedTestCaminoB(){
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyReleased(keyEvent);
        assertEquals(0, board.getPlayer().dx,"DX debería ser 0 para no moverse");
    }

    @Test
    void keyreleasedTestCaminoC(){
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyReleased(keyEvent);
        assertEquals(0, board.getPlayer().dx,"DX debería ser 0 para no moverse");
    }

}



