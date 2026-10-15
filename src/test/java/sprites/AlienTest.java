package sprites;

import main.Commons;
import org.junit.Test;
import space_invaders.sprites.Alien;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlienTest {

    @Test
    public void CP1_ValoresNominales() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void CP2_xNominal_yMinimo() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 0;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void CP3_xNominal_yMinimoPlus() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 1;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void CP4_xNominal_yGroundMenos() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.GROUND - Commons.ALIEN_HEIGHT - 1;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void CP4_xNominal_yGround() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.GROUND - Commons.ALIEN_HEIGHT;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void CP4_xNominal_yGroundPlus() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.GROUND - Commons.ALIEN_HEIGHT + 1;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && (alien.getY() + Commons.ALIEN_HEIGHT < Commons.GROUND);

        assertFalse("Alien should NOT be in that position", posicionCorrecta);
    }

    @Test
    public void CP5_xNominal_yMaximo() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.BOARD_HEIGHT - Commons.ALIEN_HEIGHT;
        Alien alien = new Alien(valorX, valorY);

        boolean posicionCorrecta = alien.getX() == valorX && (alien.getY() + Commons.ALIEN_HEIGHT < Commons.GROUND);
        assertFalse("Alien should NOT be in the correct position", posicionCorrecta);
    }

    @Test
    public void CP6_xMinimo_yNominal() {
        int valorX = 0;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void CP7_xMinimoPlus_yNominal() {
        int valorX = 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void CP8_xMaximoMenos_yNominal() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH - 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void CP9_xMaximo_yNominal() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

}
