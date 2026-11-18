package blackbox.space_invaders.sprites;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import main.Commons;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.*;

public class AlienTest {

    private static final int X_MIN = Commons.BORDER_LEFT;
    private static final int X_MIN_MENOS = X_MIN - 1;
    private static final int X_MIN_MAS = X_MIN + 1;
    private static final int X_NOMINAL = Commons.ALIEN_INIT_X;
    private static final int X_MAX = Commons.BOARD_WIDTH - Commons.BORDER_RIGHT;
    private static final int X_MAX_MENOS = X_MAX - 1;
    private static final int X_MAX_MAS = X_MAX + 1;

    private static final int Y_MIN = 0;
    private static final int Y_MIN_MENOS = Y_MIN - 1;
    private static final int Y_MIN_MAS = Y_MIN + 1;
    private static final int Y_NOMINAL = Commons.ALIEN_INIT_Y;
    private static final int Y_MAX = Commons.GROUND;
    private static final int Y_MAX_MENOS = Y_MAX - 1;
    private static final int Y_MAX_MAS = Y_MAX + 1;

    static Stream<Arguments> validAssignmentsProvider() {
        return Stream.of(
                Arguments.of(X_NOMINAL, Y_NOMINAL),

                Arguments.of(X_NOMINAL, Y_MIN),
                Arguments.of(X_NOMINAL, Y_MIN_MAS),
                Arguments.of(X_NOMINAL, Y_MAX_MENOS),
                Arguments.of(X_NOMINAL, Y_MAX),

                Arguments.of(X_MIN, Y_NOMINAL),
                Arguments.of(X_MIN_MAS, Y_NOMINAL),
                Arguments.of(X_MAX_MENOS, Y_NOMINAL),
                Arguments.of(X_MAX, Y_NOMINAL)
        );
    }

    static Stream<Arguments> invalidAssignmentsProvider() {
        return Stream.of(
                Arguments.of(X_NOMINAL, Y_MIN_MENOS, X_NOMINAL, Y_MIN),
                Arguments.of(X_NOMINAL, Y_MAX_MAS, X_NOMINAL, Y_MAX),

                Arguments.of(X_MIN_MENOS, Y_NOMINAL, X_MIN, Y_NOMINAL),
                Arguments.of(X_MAX_MAS, Y_NOMINAL, X_MAX, Y_NOMINAL)
        );
    }

    @ParameterizedTest
    @MethodSource("validAssignmentsProvider")
    void CreacionAlien_AsignacionCorrecta(int x, int y){
        Alien alien = new Alien(x, y);

        boolean asignacionCorrecta = alien.getX() == x && alien.getY() == y;
        boolean posicionValida = esPosicionValida(x, y);

        assertTrue(asignacionCorrecta, "Alien should be in the assigned position");
        assertTrue(posicionValida, "Alien's actual position should be valid");
    }

    @ParameterizedTest
    @MethodSource("invalidAssignmentsProvider")
    void CreacionAlien_AsignacionIncorrecta(int assignedX, int assignedY, int expectedX, int expectedY){
        Alien alien = new Alien(assignedX, assignedY);

        boolean asignacionCorrecta = alien.getX() == assignedX && alien.getY() == assignedY;
        boolean posicionCorrectaYEsperada = esPosicionValida(alien.getX(), alien.getY()) && alien.getX() == expectedX && alien.getY() == expectedY;

        assertFalse(asignacionCorrecta, "Alien should NOT be in the assigned position");
        assertTrue(posicionCorrectaYEsperada, "Alien position should be correct");
    }

    private static boolean esPosicionValida(int x, int y){
        boolean ejeXCorrecto = x >= X_MIN && x <= X_MAX;
        boolean ejeYCorrecto = y >= Y_MIN && y <= Y_MAX;

        return ejeXCorrecto && ejeYCorrecto;
    }


    @Test
    public void TD_AlienNoSeMueve() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;
        int direccion = 0;

        Alien alien = new Alien(valorX, valorY);
        alien.act(direccion);

        boolean posicionCorrecta = alien.getX() == valorX;
        assertTrue(posicionCorrecta, "Alien no se mueve");
    }

    @Test
    public void TD_AlienSeMueve() {
        int valorX = Commons.ALIEN_INIT_X;
        int valorY = Commons.ALIEN_INIT_Y;
        int direccion = 2;

        Alien alien = new Alien(valorX, valorY);
        alien.act(direccion);

        boolean posicionCorrecta = alien.getX() == valorX+2;
        assertTrue(posicionCorrecta, "Alien se mueve");
    }

    @Test
    public void TD_AlienNoSeMueveIzquierda() {
        int valorX = 0;
        int valorY = Commons.ALIEN_INIT_Y;
        int direccion = -2;

        Alien alien = new Alien(valorX, valorY);
        alien.act(direccion);

        boolean posicionCorrecta = alien.getX() == valorX;
        assertTrue(posicionCorrecta, "Alien no se mueve");
    }

    @Test
    public void TD_AlienNoSeMueveDerecha() {
        int valorX = Commons.BOARD_WIDTH - Commons.ALIEN_WIDTH;
        int valorY = Commons.ALIEN_INIT_Y;
        int direccion = 2;

        Alien alien = new Alien(valorX, valorY);
        alien.act(direccion);

        boolean posicionCorrecta = alien.getX() == valorX;
        assertTrue(posicionCorrecta, "Alien no se mueve");
    }

}
