package space_invaders.sprites;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AlienTest {

    @Test
    public void creacionAlienPosicionCorrecta() {
        int valorx = 10;
        int valory = 10;
        Alien alien = new Alien(valorx, valory);

        boolean posicionCorrecta = alien.getX() == valorx && alien.getY() == valory;

        assertTrue("Alien should be in the correct position", posicionCorrecta);
    }
}
