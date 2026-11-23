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
    public static final int MOV_DER = 2;
    public static final int MOV_IZQ = -MOV_DER;

    static Stream<Arguments> creacionAlienProvider() {
        return Stream.of(
                // Casos válidos: la posición esperada coincide con la asignada (CN-01 a CN-09)
                Arguments.of("CN-01", X_NOMINAL, Y_NOMINAL, X_NOMINAL, Y_NOMINAL),

                Arguments.of("CN-02", X_NOMINAL, Y_MIN, X_NOMINAL, Y_MIN),
                Arguments.of("CN-03", X_NOMINAL, Y_MIN_MAS, X_NOMINAL, Y_MIN_MAS),
                Arguments.of("CN-04", X_NOMINAL, Y_MAX_MENOS, X_NOMINAL, Y_MAX_MENOS),
                Arguments.of("CN-05", X_NOMINAL, Y_MAX, X_NOMINAL, Y_MAX),

                Arguments.of("CN-06", X_MIN, Y_NOMINAL, X_MIN, Y_NOMINAL),
                Arguments.of("CN-07", X_MIN_MAS, Y_NOMINAL, X_MIN_MAS, Y_NOMINAL),
                Arguments.of("CN-08", X_MAX_MENOS, Y_NOMINAL, X_MAX_MENOS, Y_NOMINAL),
                Arguments.of("CN-09", X_MAX, Y_NOMINAL, X_MAX, Y_NOMINAL),

                // Casos inválidos: la coordenada fuera de rango se satura al límite (CN-10 a CN-13)
                Arguments.of("CN-10", X_NOMINAL, Y_MIN_MENOS, X_NOMINAL, Y_MIN),
                Arguments.of("CN-11", X_NOMINAL, Y_MAX_MAS, X_NOMINAL, Y_MAX),

                Arguments.of("CN-12", X_MIN_MENOS, Y_NOMINAL, X_MIN, Y_NOMINAL),
                Arguments.of("CN-13", X_MAX_MAS, Y_NOMINAL, X_MAX, Y_NOMINAL)
        );
    }

    @ParameterizedTest(name = "{0}: entrada=({1}, {2}) -> esperado=({3}, {4})")
    @MethodSource("creacionAlienProvider")
    void creacionAlien_PosicionEsperada(String id, int assignedX, int assignedY, int expectedX, int expectedY) {
        Alien alien = new Alien(assignedX, assignedY);

        assertEquals(expectedX, alien.getX(), id + " - Coordenada X incorrecta");
        assertEquals(expectedY, alien.getY(), id + " - Coordenada Y incorrecta");
    }

    static Stream<Arguments> movimientoAlienProvider() {
        return Stream.of(
                // Casos donde el alienígena NO se mueve (xEsperada == valorX)
                Arguments.of("CN-14", Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y, 0, Commons.ALIEN_INIT_X),
                Arguments.of("CN-15", X_MIN, Commons.ALIEN_INIT_Y, MOV_IZQ, X_MIN),
                Arguments.of("CN-16", X_MAX, Commons.ALIEN_INIT_Y, MOV_DER, X_MAX),

                // Casos donde el alienígena SÍ se mueve (xEsperada == valorX + direccion)
                Arguments.of("CN-17", Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y, MOV_IZQ, Commons.ALIEN_INIT_X + MOV_IZQ),
                Arguments.of("CN-18", Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y, MOV_DER, Commons.ALIEN_INIT_X + MOV_DER)
        );
    }

    @ParameterizedTest(name = "{0}: x={1}, y={2}, dir={3} -> xEsperada={4}")
    @MethodSource("movimientoAlienProvider")
    void moverAlien_PosicionEsperada(String id, int valorX, int valorY, int direccion, int xEsperada) {
        Alien alien = new Alien(valorX, valorY);

        alien.act(direccion);
        int xDespues = alien.getX();

        assertEquals(xEsperada, xDespues, id + " - La coordenada X tras mover el alien no es la esperada");
    }

}
