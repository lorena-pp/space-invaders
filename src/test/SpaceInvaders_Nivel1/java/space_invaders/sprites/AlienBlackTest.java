package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AlienBlackTest {

    public static final int X_MIN = Commons.BORDER_LEFT;
    public static final int X_MIN_MENOS = X_MIN - 1;
    public static final int X_MIN_MAS = X_MIN + 1;
    public static final int X_NOMINAL = Commons.ALIEN_INIT_X;
    public static final int X_MAX = Commons.BOARD_WIDTH - Commons.BORDER_RIGHT;
    public static final int X_MAX_MENOS = X_MAX - 1;
    public static final int X_MAX_MAS = X_MAX + 1;

    public static final int Y_MIN = 0;
    public static final int Y_MIN_MENOS = Y_MIN - 1;
    public static final int Y_MIN_MAS = Y_MIN + 1;
    public static final int Y_NOMINAL = Commons.ALIEN_INIT_Y;
    public static final int Y_MAX = Commons.BOARD_HEIGHT;
    public static final int Y_MAX_MENOS = Y_MAX - 1;
    public static final int Y_MAX_MAS = Y_MAX + 1;

    static Stream<Arguments> validAssignmentsProvider() {
        return Stream.of(
                Arguments.of("CN-01", X_NOMINAL, Y_NOMINAL),

                Arguments.of("CN-02", X_NOMINAL, Y_MIN),
                Arguments.of("CN-03", X_NOMINAL, Y_MIN_MAS),
                Arguments.of("CN-04", X_NOMINAL, Y_MAX_MENOS),
                Arguments.of("CN-05", X_NOMINAL, Y_MAX),

                Arguments.of("CN-06", X_MIN, Y_NOMINAL),
                Arguments.of("CN-07", X_MIN_MAS, Y_NOMINAL),
                Arguments.of("CN-08", X_MAX_MENOS, Y_NOMINAL),
                Arguments.of("CN-09", X_MAX, Y_NOMINAL)
        );
    }

    static Stream<Arguments> invalidAssignmentsProvider() {
        return Stream.of(
                Arguments.of("CN-10", X_NOMINAL, Y_MIN_MENOS, X_NOMINAL, Y_MIN),
                Arguments.of("CN-11", X_NOMINAL, Y_MAX_MAS, X_NOMINAL, Y_MAX),

                Arguments.of("CN-12", X_MIN_MENOS, Y_NOMINAL, X_MIN, Y_NOMINAL),
                Arguments.of("CN-13", X_MAX_MAS, Y_NOMINAL, X_MAX, Y_NOMINAL)
        );
    }

    @ParameterizedTest(name = "{0}: entrada=({1}, {2}) -> esperado=({1}, {2})")
    @MethodSource("validAssignmentsProvider")
    void creacionAlien_AsignacionCorrecta(String id, int x, int y) {
        Alien alien = new Alien(x, y);

        assertEquals(alien.getX(), x);
        assertEquals(alien.getY(), y);
    }

    @ParameterizedTest(name = "{0}: entrada=({1}, {2}) -> esperado=({3}, {4})")
    @MethodSource("invalidAssignmentsProvider")
    void creacionAlien_AsignacionIncorrecta(String id, int assignedX, int assignedY, int expectedX, int expectedY) {
        Alien alien = new Alien(assignedX, assignedY);

        assertNotEquals(alien.getX(), assignedX);
        assertNotEquals(alien.getY(), assignedY);

        assertEquals(alien.getX(), expectedX);
        assertEquals(alien.getY(), expectedY);
    }

    static Stream<Arguments> alienNoSeMueveProvider() {
        return Stream.of(
                // Dirección neutra en posición inicial
                Arguments.of("CN-14", Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y, 0),
                // Intento de movimiento fuera del límite izquierdo
                Arguments.of("CN-15", X_MIN, Commons.ALIEN_INIT_Y, -2),
                // Intento de movimiento fuera del límite derecho
                Arguments.of("CN-16", X_MAX, Commons.ALIEN_INIT_Y, 2)
        );
    }

    static Stream<Arguments> alienSeMueveProvider() {
        return Stream.of(
                Arguments.of("CN-17", Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y, 2),
                Arguments.of("CN-18", Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y, -2)
        );
    }

    @ParameterizedTest(name = "{0}: x={1}, y={2}, dir={3}")
    @MethodSource("alienNoSeMueveProvider")
    void moverAlien_NoSeMueve(String id, int valorX, int valorY, int direccion) {
        Alien alien = new Alien(valorX, valorY);
        int xAntes = alien.getX();

        alien.act(direccion);
        int xDespues = alien.getX();

        assertEquals(xAntes, xDespues);
    }

    @ParameterizedTest(name = "{0}: x={1}, y={2}, dir={3}")
    @MethodSource("alienSeMueveProvider")
    void moverAlien_seMueve(String id, int valorX, int valorY, int direccion) {
        Alien alien = new Alien(valorX, valorY);
        int xAntes = alien.getX();

        alien.act(direccion);
        int xDespues = alien.getX();

        assertEquals(xAntes + direccion, xDespues);
    }

}
