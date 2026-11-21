package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BombWhiteTest {

    @Test
    public void initBombCaminoA() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);

        assertEquals(Commons.ALIEN_INIT_X, bomb.getX());
        assertEquals(Commons.ALIEN_INIT_Y, bomb.getY());
    }

    @Test
    public void initBombCaminoB() {
        Alien alien = new Alien(AlienTest.X_MAX_MAS, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(AlienTest.X_MAX_MAS, Commons.ALIEN_INIT_Y);

        assertEquals(AlienTest.X_MAX, bomb.getX());
        assertEquals(Commons.ALIEN_INIT_Y, bomb.getY());
    }

    @Test
    public void initBombCaminoC() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, AlienTest.Y_MAX_MAS);
        Alien.Bomb bomb = alien.new Bomb(Commons.ALIEN_INIT_X, AlienTest.Y_MAX_MAS);

        assertEquals(Commons.ALIEN_INIT_X, bomb.getX());
        assertEquals(AlienTest.Y_MAX, bomb.getY());
    }
}
