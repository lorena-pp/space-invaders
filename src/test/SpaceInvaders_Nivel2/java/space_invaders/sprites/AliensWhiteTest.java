package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AliensWhiteTest {

    @Test
    public void initAliens_CaminoA() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);

        assertEquals(alien.getBomb().getX(), alien.getX());
        assertEquals(alien.getBomb().getY(), alien.getY());
    }

    @Test
    public void initAliens_CaminoB() {
        Alien alien = new Alien(Commons.BOARD_WIDTH+1, Commons.ALIEN_INIT_Y);

        assertEquals(AlienBlackTest.X_MAX, alien.getBomb().getX());
        assertEquals(alien.getBomb().getY(), alien.getY());
    }

    @Test
    public void initAliens_CaminoC() {
        Alien alien = new Alien(-1, Commons.ALIEN_INIT_Y);

        assertEquals(AlienBlackTest.X_MIN, alien.getBomb().getX());
        assertEquals(alien.getBomb().getY(), alien.getY());
    }

    @Test
    public void initAliens_CaminoD() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, AlienBlackTest.Y_MAX_MAS);

        assertEquals(alien.getBomb().getX(), alien.getX());
        assertEquals(AlienBlackTest.Y_MAX, alien.getBomb().getY());
    }

    @Test
    public void initAliens_CaminoE() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, AlienBlackTest.Y_MIN_MENOS);

        assertEquals(alien.getBomb().getX(), alien.getX());
        assertEquals(AlienBlackTest.Y_MIN, alien.getBomb().getY());
    }

}
