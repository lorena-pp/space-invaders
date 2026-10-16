package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlienTest {

    @Test
    void CreacionAlien_CP1_ValoresNominales() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue(asignacionCorrecta, "Alien should be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP2_xNominal_yMinimoMenos() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = -1;
        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertFalse(asignacionCorrecta, "Alien should NOT be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP2_xNominal_yMinimo() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 0;
        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue(asignacionCorrecta, "Alien should be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP3_xNominal_yMinimoPlus() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = 1;
        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue(asignacionCorrecta, "Alien should be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP4_xNominal_yGroundMenos() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.GROUND - Commons.ALIEN_HEIGHT - 1;
        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue(asignacionCorrecta, "Alien should be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP4_xNominal_yGround() {
        int valorX = Commons.ALIEN_INIT_X;

        // Asignación incorrecta: el enemigo ha alcanzado el Ground
        int valorY = Commons.GROUND - Commons.ALIEN_HEIGHT;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertFalse(asignacionCorrecta, "Alien should NOT be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP4_xNominal_yGroundPlus() {
        int valorX = Commons.ALIEN_INIT_X;

        // Asignación incorrecta: el enemigo ha alcanzado el Ground
        int valorY = Commons.GROUND - Commons.ALIEN_HEIGHT + 1;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertFalse(asignacionCorrecta, "Alien should NOT be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP6_xMinimoMenos_yNominal() {
        int valorX = -1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertFalse(asignacionCorrecta, "Alien should NOT be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP6_xMinimo_yNominal() {
        int valorX = 0;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);
        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue(asignacionCorrecta, "Alien should be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP7_xMinimoPlus_yNominal() {
        int valorX = 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue(asignacionCorrecta, "Alien should be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP8_xMaximoMenos_yNominal() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH - 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue(asignacionCorrecta, "Alien should be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP9_xMaximo_yNominal() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertTrue(asignacionCorrecta, "Alien should be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    @Test
    void CreacionAlien_CP9_xMaximoPlus_yNominal() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH + 1;
        int valorY = Commons.ALIEN_INIT_Y;

        Alien alien = new Alien(valorX, valorY);

        boolean asignacionCorrecta = alien.getX() == valorX && alien.getY() == valorY;
        boolean posicionCorrecta = esPosicionValida(alien.getX(), alien.getY());

        assertFalse(asignacionCorrecta, "Alien should NOT be in the assigned position");
        assertTrue(posicionCorrecta, "Alien position should be correct");
    }

    private boolean esPosicionValida(int x, int y){
        boolean outInX = (x - Commons.ALIEN_WIDTH) <= Commons.BOARD_WIDTH;
        boolean outGround = (y - Commons.ALIEN_HEIGHT) < Commons.BOARD_HEIGHT;
        return outInX && outGround;
    }

    @Test
    public void TD_AlienNoSeMueve() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;
        int direccion = 0;

        Alien alien = new Alien(valorX, valorY);
        alien.act(direccion);

        boolean posicionCorrecta = alien.getX() == valorX;
        assertTrue(posicionCorrecta, "Alien no se mueve");
    }

    @Test
    public void TD_AlienSeMueveIzquierda() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;
        int direccion = -2;

        Alien alien = new Alien(valorX, valorY);
        alien.act(direccion);

        boolean posicionCorrecta = alien.getX() == valorX-2;
        assertTrue(posicionCorrecta, "Alien se mueve a la izquierda");
    }

    @Test
    public void TD_AlienNoSeMueveIzquierda() {
        int valorX = 0;
        int valorY = Commons.ALIEN_INIT_Y;
        int direccion = -2;

        Alien alien = new Alien(valorX, valorY);
        alien.act(direccion);

        boolean posicionCorrecta = alien.getX() == valorX;
        assertTrue(posicionCorrecta, "Alien no se mueve");
    }

    @Test
    public void TD_AlienSeMueveDerecha() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;
        int direccion = 2;

        Alien alien = new Alien(valorX, valorY);
        alien.act(direccion);

        boolean posicionCorrecta = alien.getX() == valorX+2;
        assertTrue(posicionCorrecta, "Alien se mueve a la  derecha");
    }

    @Test
    public void TD_AlienNoSeMueveDerecha() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH;
        int valorY = Commons.ALIEN_INIT_Y;
        int direccion = 2;

        Alien alien = new Alien(valorX, valorY);
        alien.act(direccion);

        boolean posicionCorrecta = alien.getX() == valorX;
        assertTrue(posicionCorrecta, "Alien no se mueve");
    }

}
