package space_invaders.sprites;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerWhiteTest {

    @Test
    void keyPressed_CaminoA() {
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyPressed(keyEvent);
        assertEquals(0, board.getPlayer().dx, "DX debería ser 0 para no moverse");
    }

    @Test
    void keyPressed_CaminoB() {
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyPressed(keyEvent);
        assertEquals(2, board.getPlayer().dx, "DX debería ser 2 para irse a la derecha");
    }

    @Test
    void keyPressed_CaminoC() {
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyPressed(keyEvent);
        assertEquals(-2, board.getPlayer().dx, "DX debería ser -2 para irse a la izquierda");
    }

    @Test
    void keyReleased_CaminoA() {
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyReleased(keyEvent);
        assertEquals(0, board.getPlayer().dx, "DX debería ser 0 para no moverse");
    }

    @Test
    void keyReleased_CaminoB() {
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyReleased(keyEvent);
        assertEquals(0, board.getPlayer().dx, "DX debería ser 0 para no moverse");
    }

    @Test
    void keyReleased_CaminoC() {
        Board board = new Board();
        KeyEvent keyEvent = new KeyEvent(board, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        board.getPlayer().keyReleased(keyEvent);
        assertEquals(0, board.getPlayer().dx, "DX debería ser 0 para no moverse");
    }

    @Test
    void act_CaminoA() {
        Player player = new Player();

        player.setX(Commons.BOARD_WIDTH / 2);
        player.dx = PlayerBlackTest.MOV_IZQUIERDA;

        player.act();

        assertEquals(Commons.BOARD_WIDTH / 2 + PlayerBlackTest.MOV_IZQUIERDA, player.getX());
    }

    @Test
    void act_CaminoB() {
        Player player = new Player();

        player.setX(2);
        player.dx = PlayerBlackTest.MOV_IZQUIERDA;

        player.act();

        assertEquals(AlienBlackTest.X_MIN, player.getX());
    }

    @Test
    void act_CaminoC() {
        Player player = new Player();

        player.setX(Commons.BOARD_WIDTH * 2 * Commons.PLAYER_WIDTH);
        player.dx = PlayerBlackTest.MOV_DERECHA;

        player.act();

        assertEquals(AlienBlackTest.X_MAX - 2 - Commons.PLAYER_WIDTH, player.getX());
    }

    @Test
    void initPlayer_CaminoA() {
        Player player = new Player();

        assertEquals(Commons.BOARD_WIDTH / 2, player.getX());
        assertEquals(Commons.GROUND - 10, player.getY());
    }

    @Test
    void initPlayer_CaminoB() {
        // camino imposible. La inicialización de la imagen se hace desde el constructor, y además no tengo manera de
        // cambiar la imagen ya que se declara dentro del método initPlayer()
    }

}
