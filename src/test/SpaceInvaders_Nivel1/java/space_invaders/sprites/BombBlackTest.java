package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static space_invaders.sprites.AlienBlackTest.*;

public class BombBlackTest {

    @Nested
    public class BombStandardTests {

        private Alien alien;

        @BeforeEach
        void setUp() {
            alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        }

        static Stream<Arguments> validAssignmentsProvider() {
            return Stream.of(
                    Arguments.of("CN-14", X_NOMINAL, Y_NOMINAL),

                    Arguments.of("CN-15", X_NOMINAL, Y_MIN),
                    Arguments.of("CN-16", X_NOMINAL, Y_MIN_MAS),
                    Arguments.of("CN-17", X_NOMINAL, Y_MAX_MENOS),
                    Arguments.of("CN-18", X_NOMINAL, Y_MAX),

                    Arguments.of("CN-19", X_MIN, Y_NOMINAL),
                    Arguments.of("CN-20", X_MIN_MAS, Y_NOMINAL),
                    Arguments.of("CN-21", X_MAX_MENOS, Y_NOMINAL),
                    Arguments.of("CN-22", X_MAX, Y_NOMINAL)
            );
        }

        static Stream<Arguments> invalidAssignmentsProvider() {
            return Stream.of(
                    Arguments.of("CN-23", X_NOMINAL, Y_MIN_MENOS, X_NOMINAL, Y_MIN),
                    Arguments.of("CN-24", X_NOMINAL, Y_MAX_MAS, X_NOMINAL, Y_MAX),

                    Arguments.of("CN-25", X_MIN_MENOS, Y_NOMINAL, X_MIN, Y_NOMINAL),
                    Arguments.of("CN-26", X_MAX_MAS, Y_NOMINAL, X_MAX, Y_NOMINAL)
            );
        }

        @ParameterizedTest(name = "{0}: entrada=({1}, {2}) -> esperado=({1}, {2})")
        @MethodSource("validAssignmentsProvider")
        void creacionBomba_AsignacionCorrecta(String id, int x, int y) {
            Alien.Bomb bomb = alien.new Bomb(x, y);

            assertEquals(bomb.getX(), x);
            assertEquals(bomb.getY(), y);

            assertFalse(bomb.isDestroyed());
        }

        @ParameterizedTest(name = "{0}: entrada=({1}, {2}) -> esperado=({3}, {4})")
        @MethodSource("invalidAssignmentsProvider")
        void creacionBomba_AsignacionIncorrecta(String id, int assignedX, int assignedY, int expectedX, int expectedY) {
            Alien.Bomb bomb = alien.new Bomb(assignedX, assignedY);

            assertNotEquals(bomb.getX(), assignedX);
            assertNotEquals(bomb.getY(), assignedY);

            assertEquals(bomb.getX(), expectedX);
            assertEquals(bomb.getY(), expectedY);

            assertFalse(bomb.isDestroyed());
        }
    }
}