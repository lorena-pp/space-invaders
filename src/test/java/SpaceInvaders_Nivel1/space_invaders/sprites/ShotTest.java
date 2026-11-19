package SpaceInvaders_Nivel1.space_invaders.sprites;

import main.Commons;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShotTest {

    int H_SPACE = 6;
    int V_SPACE = 1;

    @Test
    void BVT_creacionShot_valoresNominales() {
        int valor_x = Commons.BOARD_WIDTH/2;
        int valor_y = Commons.BOARD_HEIGHT/2;

        Shot shot = new Shot(valor_x, valor_y);
        int x_esperada = valor_x + H_SPACE;
        int y_esperada = valor_y - V_SPACE;

        boolean posicionCorrecta = shot.getX() == x_esperada && shot.getY() == y_esperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }

    @Test
    void BVT_creacionShot_xNominal_yMinimo() {
        int valor_x = Commons.BOARD_WIDTH/2;
        int valor_y = 0;

        Shot shot = new Shot(valor_x, valor_y);
        int x_esperada = valor_x + H_SPACE;
        int y_esperada = valor_y - V_SPACE;

        boolean posicionCorrecta = shot.getX() == x_esperada && shot.getY() == y_esperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }

    @Test
    void BVT_creacionShot_xNominal_yMinimoPlus() {
        int valor_x = Commons.BOARD_WIDTH/2;
        int valor_y = 1;

        Shot shot = new Shot(valor_x, valor_y);
        int x_esperada = valor_x + H_SPACE;
        int y_esperada = valor_y - V_SPACE;

        boolean posicionCorrecta = shot.getX() == x_esperada && shot.getY() == y_esperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }

    @Test
    void BVT_creacionShot_xNominal_yMaximoMenos() {
        int valor_x = Commons.BOARD_WIDTH/2;
        int valor_y = Commons.BOARD_HEIGHT - 1;

        Shot shot = new Shot(valor_x, valor_y);
        int x_esperada = valor_x + H_SPACE;
        int y_esperada = valor_y - V_SPACE;

        boolean posicionCorrecta = shot.getX() == x_esperada && shot.getY() == y_esperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }

    @Test
    void BVT_creacionShot_xNominal_yMaximo() {
        int valor_x = Commons.BOARD_WIDTH/2;
        int valor_y = Commons.BOARD_HEIGHT;

        Shot shot = new Shot(valor_x, valor_y);
        int x_esperada = valor_x + H_SPACE;
        int y_esperada = valor_y - V_SPACE;

        boolean posicionCorrecta = shot.getX() == x_esperada && shot.getY() == y_esperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }

    @Test
    void BVT_creacionShot_xMinimo_yNominal() {
        int valor_x = 0;
        int valor_y = Commons.BOARD_HEIGHT/2;

        Shot shot = new Shot(valor_x, valor_y);
        int x_esperada = valor_x + H_SPACE;
        int y_esperada = valor_y - V_SPACE;

        boolean posicionCorrecta = shot.getX() == x_esperada && shot.getY() == y_esperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }

    @Test
    void BVT_creacionShot_xMinimoPlus_yNominal() {
        int valor_x = 1;
        int valor_y = Commons.BOARD_HEIGHT/2;

        Shot shot = new Shot(valor_x, valor_y);
        int x_esperada = valor_x + H_SPACE;
        int y_esperada = valor_y - V_SPACE;

        boolean posicionCorrecta = shot.getX() == x_esperada && shot.getY() == y_esperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }

    @Test
    void BVT_creacionAShot_xMaximoMenos_yNominal() {
        int valor_x = Commons.BOARD_WIDTH - 1;
        int valor_y = Commons.BOARD_HEIGHT/2;

        Shot shot = new Shot(valor_x, valor_y);
        int x_esperada = valor_x + H_SPACE;
        int y_esperada = valor_y - V_SPACE;

        boolean posicionCorrecta = shot.getX() == x_esperada && shot.getY() == y_esperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }

    @Test
    void BVT_creacionShot_xMaximo_yNominal() {
        int valor_x = Commons.PLAYER_WIDTH;
        int valor_y = Commons.PLAYER_HEIGHT/2;

        Shot shot = new Shot(valor_x, valor_y);
        int x_esperada = valor_x + H_SPACE;
        int y_esperada = valor_y - V_SPACE;

        boolean posicionCorrecta = shot.getX() == x_esperada && shot.getY() == y_esperada;

        assertTrue(posicionCorrecta, "Shot should be in the correct position");
    }
}
