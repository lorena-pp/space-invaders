package space_invaders.sprites;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void actCaminoA(){
        Player player = new Player();

        player.setX(Commons.BOARD_WIDTH/2);
        player.dx = PlayerBlackTest.MOV_IZQUIERDA;

        player.act();

        assertEquals(Commons.BOARD_WIDTH/2 + PlayerBlackTest.MOV_IZQUIERDA, player.getX());
    }

    @Test
    void actCaminoB(){
        Player player = new Player();

        player.setX(2);
        player.dx = PlayerBlackTest.MOV_IZQUIERDA;

        player.act();

        assertEquals(AlienTest.X_MIN, player.getX());
    }

    @Test
    void actCaminoC(){
        Player player = new Player();

        player.setX(Commons.BOARD_WIDTH * 2 * Commons.PLAYER_WIDTH);
        player.dx = PlayerBlackTest.MOV_DERECHA;

        player.act();

        assertEquals(AlienTest.X_MAX - 2 - Commons.PLAYER_WIDTH, player.getX());
    }

    @Test
    void initPlayerCaminoA(){
        Player player = new Player();

        assertEquals(Commons.BOARD_WIDTH/2, player.getX());
        assertEquals(Commons.GROUND - 10, player.getY());
    }

    @Test
    void initPlayerCaminoB(){
        Player player = new Player();

        // hacer que entre al primer if (hacer que imageURL sea null) (no sé cómo)

        assertThrows(
                IllegalStateException.class,
                () -> new Player()
        );
    }

}



