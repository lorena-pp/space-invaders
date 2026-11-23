package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BombWhiteTest {

    @Test
    public void initBomb_CaminoA() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);

        assertEquals(Commons.ALIEN_INIT_X, bomb.getX());
        assertEquals(Commons.ALIEN_INIT_Y, bomb.getY());
    }

    @Test
    public void initBomb_CaminoB() {
        Alien alien = new Alien(AlienBlackTest.X_MAX_MAS, Commons.ALIEN_INIT_Y);
        Alien.Bomb bomb = alien.new Bomb(AlienBlackTest.X_MAX_MAS, Commons.ALIEN_INIT_Y);

        assertEquals(AlienBlackTest.X_MAX, bomb.getX());
        assertEquals(Commons.ALIEN_INIT_Y, bomb.getY());
    }

    @Test
    public void initBomb_CaminoC() {
        Alien alien = new Alien(Commons.ALIEN_INIT_X, AlienBlackTest.Y_MAX_MAS);
        Alien.Bomb bomb = alien.new Bomb(Commons.ALIEN_INIT_X, AlienBlackTest.Y_MAX_MAS);

        assertEquals(Commons.ALIEN_INIT_X, bomb.getX());
        assertEquals(AlienBlackTest.Y_MAX, bomb.getY());
    }
}
