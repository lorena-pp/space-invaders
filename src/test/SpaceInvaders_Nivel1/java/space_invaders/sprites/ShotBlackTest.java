package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShotBlackTest {

    final int H_SPACE = 6;
    final int V_SPACE = 1;
    static final int X_NOMINAL = Commons.BOARD_WIDTH / 2;
    static final int Y_NOMINAL = Commons.BOARD_HEIGHT / 2;

    static Stream<Arguments> shotPositionsProvider() {
        return Stream.of(
                Arguments.of(X_NOMINAL, Y_NOMINAL),

                Arguments.of(X_NOMINAL, AlienBlackTest.Y_MIN),
                Arguments.of(X_NOMINAL, AlienBlackTest.Y_MIN_MAS),
                Arguments.of(X_NOMINAL, AlienBlackTest.Y_MAX_MENOS),
                Arguments.of(X_NOMINAL, AlienBlackTest.Y_MAX),

                Arguments.of(AlienBlackTest.X_MIN, Y_NOMINAL),
                Arguments.of(AlienBlackTest.X_MIN_MAS, Y_NOMINAL),
                Arguments.of(AlienBlackTest.X_MAX_MENOS, Y_NOMINAL),
                Arguments.of(AlienBlackTest.X_MAX, Y_NOMINAL)
        );
    }

    @ParameterizedTest
    @MethodSource("shotPositionsProvider")
    void BVT_creacionShot(int valorX, int valorY) {
        Shot shot = new Shot(valorX, valorY);

        int xEsperada = valorX + H_SPACE;
        int yEsperada = valorY - V_SPACE;

        boolean posicionCorrecta = shot.getX() == xEsperada && shot.getY() == yEsperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }
}