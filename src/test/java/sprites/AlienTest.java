package sprites;

import main.Commons;
import org.junit.Test;
import space_invaders.sprites.Alien;

import static org.junit.Assert.assertTrue;

public class AlienTest {

    @Test
    public void BVT_creacionAlienPosicionCorrecta_valoresNominales() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionAlienPosicionCorrecta_xNominal_yMinimo() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 0;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionAlienPosicionCorrecta_xNominal_yMinimoPlus() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 1;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionAlienPosicionCorrecta_xNominal_yMaximoMenos() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.BOARD_HEIGHT - Commons.ALIEN_HEIGHT - 1;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionAlienPosicionCorrecta_xNominal_yMaximo() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.BOARD_HEIGHT - Commons.ALIEN_HEIGHT;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionAlienPosicionCorrecta_xMinimo_yNominal() {
        int valorX = 0;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionAlienPosicionCorrecta_xMinimoPlus_yNominal() {
        int valorX = 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionAlienPosicionCorrecta_xMaximoMenos_yNominal() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH - 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void BVT_creacionAlienPosicionCorrecta_xMaximo_yNominal() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

}
