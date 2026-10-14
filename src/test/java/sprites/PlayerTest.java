package sprites;

import main.Commons;
import org.junit.Test;
import space_invaders.sprites.Player;

import static org.junit.Assert.assertTrue;

public class PlayerTest {

    @Test
    public void CLASE_DE_EQUIVALENCIA_TESTING_creacionPlayerPosicionCorrecta_valoresNominales() {
        int valorX = Commons.BOARD_WIDTH/2;
        int valorY = Commons.GROUND-10; //el enunciado dice que está 10 píxeles por encima del ground

        Player player = new Player();
        boolean posicionCorrecta = player.getX() == valorX && player.getY() == valorY;

        assertTrue("Player should be in the correct position", posicionCorrecta);
    }
}

// int valorX_minimo = Commons.PLAYER_WIDTH/2
// int valorX_maximo = Commons.BOARD_WIDTH - Commons.PLAYER_WIDTH/2
