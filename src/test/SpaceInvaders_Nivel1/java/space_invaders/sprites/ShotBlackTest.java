package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ShotBlackTest {

    static final int H_SPACE = 6;
    static final int V_SPACE = 1;
    static final int X_NOMINAL = Commons.BOARD_WIDTH / 2;
    static final int Y_NOMINAL = Commons.BOARD_HEIGHT / 2;

    static Stream<Arguments> creacionShotProvider() {
        return Stream.of(
                // Casos dentro de los límites del tablero (CN-46 a CN-54)
                Arguments.of("CN-46", X_NOMINAL, Y_NOMINAL, X_NOMINAL + H_SPACE, Y_NOMINAL - V_SPACE),

                Arguments.of("CN-47", X_NOMINAL, AlienBlackTest.Y_MIN, X_NOMINAL + H_SPACE, AlienBlackTest.Y_MIN - V_SPACE),
                Arguments.of("CN-48", X_NOMINAL, AlienBlackTest.Y_MIN_MAS, X_NOMINAL + H_SPACE, AlienBlackTest.Y_MIN_MAS - V_SPACE),
                Arguments.of("CN-49", X_NOMINAL, AlienBlackTest.Y_MAX_MENOS, X_NOMINAL + H_SPACE, AlienBlackTest.Y_MAX_MENOS - V_SPACE),
                Arguments.of("CN-50", X_NOMINAL, AlienBlackTest.Y_MAX, X_NOMINAL + H_SPACE, AlienBlackTest.Y_MAX - V_SPACE),

                Arguments.of("CN-51", AlienBlackTest.X_MIN, Y_NOMINAL, AlienBlackTest.X_MIN + H_SPACE, Y_NOMINAL - V_SPACE),
                Arguments.of("CN-52", AlienBlackTest.X_MIN_MAS, Y_NOMINAL, AlienBlackTest.X_MIN_MAS + H_SPACE, Y_NOMINAL - V_SPACE),
                Arguments.of("CN-53", AlienBlackTest.X_MAX_MENOS, Y_NOMINAL, AlienBlackTest.X_MAX_MENOS + H_SPACE, Y_NOMINAL - V_SPACE),
                Arguments.of("CN-54", AlienBlackTest.X_MAX, Y_NOMINAL, AlienBlackTest.X_MAX + H_SPACE, Y_NOMINAL - V_SPACE),

                // Casos fuera de los límites del tablero: según el Javadoc no saturan, aplican el mismo desplazamiento (CN-55 a CN-58)
                Arguments.of("CN-55", X_NOMINAL, AlienBlackTest.Y_MIN_MENOS, X_NOMINAL + H_SPACE, AlienBlackTest.Y_MIN_MENOS - V_SPACE),
                Arguments.of("CN-56", X_NOMINAL, AlienBlackTest.Y_MAX_MAS, X_NOMINAL + H_SPACE, AlienBlackTest.Y_MAX_MAS - V_SPACE),

                Arguments.of("CN-57", AlienBlackTest.X_MIN_MENOS, Y_NOMINAL, AlienBlackTest.X_MIN_MENOS + H_SPACE, Y_NOMINAL - V_SPACE),
                Arguments.of("CN-58", AlienBlackTest.X_MAX_MAS, Y_NOMINAL, AlienBlackTest.X_MAX_MAS + H_SPACE, Y_NOMINAL - V_SPACE)
        );
    }

    @ParameterizedTest(name = "{0}: entrada=({1}, {2}) -> esperado=({3}, {4})")
    @MethodSource("creacionShotProvider")
    void creacionShot_PosicionEsperada(String id, int assignedX, int assignedY, int expectedX, int expectedY) {
        Shot shot = new Shot(assignedX, assignedY);

        assertEquals(expectedX, shot.getX(), id + " - Coordenada X del disparo incorrecta");
        assertEquals(expectedY, shot.getY(), id + " - Coordenada Y del disparo incorrecta");
    }
}