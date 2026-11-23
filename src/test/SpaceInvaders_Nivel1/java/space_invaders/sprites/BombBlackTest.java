package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import space_invaders.sprites.Alien;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BombBlackTest {

    @Nested
    public class BombStandardTests {

        @Test
        public void creacionBombCorrectoEstado() {
            int valorX = Commons.BOARD_WIDTH;
            int valorY = Commons.ALIEN_INIT_Y;

            Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
            Alien.Bomb bomb = alien.new Bomb(valorX, valorY);

            assertFalse(bomb.isDestroyed(), "Bomb shouldn't be initialized as destroyed");
        }
    }

    @Nested
    public class BombPosicionParameterizedTest {

        public static Stream<Arguments> data() {
            return Stream.of(
                    Arguments.of(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y),
                    Arguments.of(Commons.ALIEN_INIT_X, 0),
                    Arguments.of(Commons.ALIEN_INIT_X, 1),
                    Arguments.of(Commons.ALIEN_INIT_X, Commons.BOARD_HEIGHT - 1),
                    Arguments.of(Commons.ALIEN_INIT_X, Commons.BOARD_HEIGHT),
                    Arguments.of(0, Commons.ALIEN_INIT_Y),
                    Arguments.of(1, Commons.ALIEN_INIT_Y),
                    Arguments.of(Commons.BOARD_WIDTH - 1, Commons.ALIEN_INIT_Y),
                    Arguments.of(Commons.BOARD_WIDTH, Commons.ALIEN_INIT_Y)
            );
        }

        @ParameterizedTest(name = "Bomba pos X:{0} Y:{1}")
        @MethodSource("data")
        public void BVT_creacionBombaPosicionCorrectaParametrizada(int valorX, int valorY) {
            Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
            Alien.Bomb bomb = alien.new Bomb(valorX, valorY);
            boolean posicionCorrecta = bomb.getX() == valorX && bomb.getY() == valorY;

            assertTrue(posicionCorrecta, "Bomb should be in the correct position");
        }
    }
}