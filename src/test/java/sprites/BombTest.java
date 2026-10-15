package sprites;

import main.Commons;
import org.junit.Test;
import space_invaders.sprites.Alien;

import static org.junit.Assert.assertTrue;
public class BombTest {
    @Test
    public void BVT_creacionBombaPosicionCorrecta_valoresNominales() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
        boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

        assertTrue("Bomb should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionBombaPosicionCorrecta_xNominal_yMinimo() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 0;

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
        boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

        assertTrue("Bomb should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionBombaPosicionCorrecta_xNominal_yMinimoPlus() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 1;

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
        boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

        assertTrue("Bomb should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionBombaPosicionCorrecta_xNominal_yMaximoMenos() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.BOARD_HEIGHT - 1;

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
        boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

        assertTrue("Bomb should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionBombaPosicionCorrecta_xNominal_yMaximo() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.BOARD_HEIGHT;

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
        boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

        assertTrue("Bomb should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionBombaPosicionCorrecta_xMinimo_yNominal() {
        int valorX = 0;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
        boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

        assertTrue("Bomb should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionBombaPosicionCorrecta_xMinimoPlus_yNominal() {
        int valorX = 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
        boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

        assertTrue("Bomb should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionBombaPosicionCorrecta_xMaximoMenos_yNominal() {
        int valorX = Commons.BOARD_WIDTH - 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
        boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

        assertTrue("Bomb should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionBombaPosicionCorrecta_xMaximo_yNominal() {
        int valorX = Commons.BOARD_WIDTH;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
        boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

        assertTrue("Bomb should be in the correct position", posicionCorrecta);
    }
}
