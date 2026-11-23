package space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static space_invaders.sprites.AlienBlackTest.*;

public class BombBlackTest {

    private Alien alien;

    @BeforeEach
    void setUp() {
        alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
    }

    static Stream<Arguments> creacionBombaProvider() {
        return Stream.of(
                // Casos válidos
                Arguments.of("CN-19", X_NOMINAL, Y_NOMINAL, X_NOMINAL, Y_NOMINAL),

                Arguments.of("CN-20", X_NOMINAL, Y_MIN, X_NOMINAL, Y_MIN),
                Arguments.of("CN-21", X_NOMINAL, Y_MIN_MAS, X_NOMINAL, Y_MIN_MAS),
                Arguments.of("CN-22", X_NOMINAL, Y_MAX_MENOS, X_NOMINAL, Y_MAX_MENOS),
                Arguments.of("CN-23", X_NOMINAL, Y_MAX, X_NOMINAL, Y_MAX),

                Arguments.of("CN-24", X_MIN, Y_NOMINAL, X_MIN, Y_NOMINAL),
                Arguments.of("CN-25", X_MIN_MAS, Y_NOMINAL, X_MIN_MAS, Y_NOMINAL),
                Arguments.of("CN-26", X_MAX_MENOS, Y_NOMINAL, X_MAX_MENOS, Y_NOMINAL),
                Arguments.of("CN-27", X_MAX, Y_NOMINAL, X_MAX, Y_NOMINAL),

                // Casos inválidos
                Arguments.of("CN-28", X_NOMINAL, Y_MIN_MENOS, X_NOMINAL, Y_MIN),
                Arguments.of("CN-29", X_NOMINAL, Y_MAX_MAS, X_NOMINAL, Y_MAX),

                Arguments.of("CN-30", X_MIN_MENOS, Y_NOMINAL, X_MIN, Y_NOMINAL),
                Arguments.of("CN-31", X_MAX_MAS, Y_NOMINAL, X_MAX, Y_NOMINAL)
        );
    }

    @ParameterizedTest(name = "{0}: entrada=({1}, {2}) -> esperado=({3}, {4})")
    @MethodSource("creacionBombaProvider")
    void creacionBomba_PosicionEsperada(String id, int assignedX, int assignedY, int expectedX, int expectedY) {
        Alien.Bomb bomb = alien.new Bomb(assignedX, assignedY);

        assertEquals(expectedX, bomb.getX(), id + " - Coordenada X de la bomba incorrecta");
        assertEquals(expectedY, bomb.getY(), id + " - Coordenada Y de la bomba incorrecta");
        assertTrue(bomb.isDestroyed(), id + " - La bomba debe inicializarse destruida para que no se muestre hasta que el Alien no la lance");
    }
}