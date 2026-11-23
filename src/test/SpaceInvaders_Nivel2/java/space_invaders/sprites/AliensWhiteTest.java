package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AliensWhiteTest {

    @Test
    public void initAliensCaminoA() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);

        assertEquals(alien.getBomb().getX(), alien.getX());
        assertEquals(alien.getBomb().getY(), alien.getY());
    }

    @Test
    public void initAliensCaminoB() {
        Alien alien = new Alien(AlienBlackTest.X_MAX_MAS, Commons.ALIEN_INIT_Y);

        assertEquals(AlienBlackTest.X_MAX, alien.getBomb().getX());
        assertEquals(alien.getBomb().getY(), alien.getY());
    }

    @Test
    public void initAliensCaminoC() {
        Alien alien = new Alien(AlienBlackTest.X_MIN_MENOS, Commons.ALIEN_INIT_Y);

        assertEquals(AlienBlackTest.X_MIN, alien.getBomb().getX());
        assertEquals(alien.getBomb().getY(), alien.getY());
    }

    @Test
    public void initAliensCaminoD() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, AlienBlackTest.Y_MAX_MAS);

        assertEquals(alien.getBomb().getX(), alien.getX());
        assertEquals(AlienBlackTest.Y_MAX, alien.getBomb().getY());
    }

    @Test
    public void initAliensCaminoE() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, AlienBlackTest.Y_MIN_MENOS);

        assertEquals(alien.getBomb().getX(), alien.getX());
        assertEquals(AlienBlackTest.Y_MIN, alien.getBomb().getY());
    }

}
