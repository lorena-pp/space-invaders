package SpaceInvaders_Nivel2VERSIONPAFU.Main;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void updateCaminoA() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Board testBoard = new Board();
        testBoard.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);

        Method updateMethod = Board.class.getDeclaredMethod("update");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertEquals("Game won!", testBoard.getMessage());

    }
    @Test
    public void updateCaminoB() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY+1);

        Method updateMethod = Board.class.getDeclaredMethod("update");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertEquals("Game Over", testBoard.getMessage());
    }













    @Test
    public void updateShotsCaminoD() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getAliens().clear();

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertFalse(
                testBoard.getShot().isVisible(),
                "El disparo sigue dentro de los límites del board"
        );
    }


    @Test
    public void updateShotsCaminoE() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        int prevpos = testBoard.getShot().getY();
        testBoard.getAliens().clear();

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertTrue(
                testBoard.getShot().getY() == prevpos,
                "La posición del disparo se ha actualizado"
        );
    }

    @Test
    public void updateShotsCaminoF() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        int prevdeaths = testBoard.getDeaths();
        int prevpos = testBoard.getShot().getY();
        testBoard.getShot().die();

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertTrue(
                testBoard.getDeaths() == prevdeaths && testBoard.getShot().getY() == prevpos,
                "El disparo no es visible"
        );
        //EL DEATHS++ DE AL FINAL DEL METODO DE UPDATE SHOTS DEBERÍA SER THIS.SHOT.DIE()
    }



    /*
    private void update_shots() {
        if (this.shot.isVisible()) {

            int shotX = this.shot.getX();
            int shotY = this.shot.getY();

            for (Alien alien : this.aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && this.shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {

                        var ii = new ImageIcon(explImg);
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        deaths++;
                        this.shot.die();
                    }
                }
            }

            int y = this.shot.getY();
            y -= Commons.SHOT_SPEED;

            if (y < 0) {
                deaths++;
            } else {
                this.shot.setY(y);

            }
        }
    }
     */
}

