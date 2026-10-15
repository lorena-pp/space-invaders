package space_invaders.sprites;

import main.Commons;
import org.junit.Test;
import space_invaders.sprites.Alien;

import static org.junit.Assert.*;

public class AlienTest {

    @Test
    public void CreacionAlien_CP1_ValoresNominales() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue("Alien should be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP2_xNominal_yMinimo() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 0;
        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue("Alien should be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP3_xNominal_yMinimoPlus() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 1;
        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue("Alien should be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP4_xNominal_yGroundMenos() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.GROUND - Commons.ALIEN_HEIGHT - 1;
        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue("Alien should be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP4_xNominal_yGround() {
        int valorX = Commons.ALIEN_INIT_X;

        // Asignación incorrecta: el enemigo ha alcanzado el Ground
        int valorY = Commons.GROUND - Commons.ALIEN_HEIGHT;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertFalse("Alien should NOT be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP4_xNominal_yGroundPlus() {
        int valorX = Commons.ALIEN_INIT_X;

        // Asignación incorrecta: el enemigo ha alcanzado el Ground
        int valorY = Commons.GROUND - Commons.ALIEN_HEIGHT + 1;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertFalse("Alien should NOT be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP5_xNominal_yMaximoMenos() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.BOARD_HEIGHT - Commons.ALIEN_HEIGHT - 1;
        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertFalse("Alien should NOT be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP5_xNominal_yMaximo() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.BOARD_HEIGHT - Commons.ALIEN_HEIGHT;
        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertFalse("Alien should NOT be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP6_xMinimo_yNominal() {
        int valorX = 0;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue("Alien should be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP7_xMinimoPlus_yNominal() {
        int valorX = 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue("Alien should be in the assigned position", asignacionCorrecta);
        assertTrue("Alien position should be correct", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP8_xMaximoMenos_yNominal() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH - 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    @Test
    public void CreacionAlien_CP9_xMaximo_yNominal() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean posicionCorrecta = alien.getX() == valorX && alien.getY() == valorY;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }

    private boolean esPosicionValida(int x, int y){
        boolean outInX = (x - Commons.ALIEN_WIDTH) <= Commons.BOARD_WIDTH;
        boolean outGround = (y - Commons.ALIEN_HEIGHT) < Commons.BOARD_HEIGHT;
        return outInX && outGround;
    }

}
