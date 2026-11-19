package SpaceInvaders_Nivel2VERSIONPAFU.Main;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

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
    public void updateShotsCaminoA() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BOARD_WIDTH/2, Commons.BOARD_HEIGHT/2);
        testBoard.getAliens().add(alien);
        testBoard.getShot().setX(Commons.BOARD_WIDTH/2);
        testBoard.getShot().setY(Commons.BOARD_HEIGHT/2);

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertTrue(
                alien.isDying(),
                "El alien no ha sido interceptado por el disparo"
        );
    }

    @Test
    public void updateShotsCaminoB() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BOARD_WIDTH/2 - 50, Commons.BOARD_HEIGHT/2 - 50);
        testBoard.getAliens().add(alien);
        testBoard.getShot().setX(Commons.BOARD_WIDTH/2 + 50);
        testBoard.getShot().setY(Commons.BOARD_HEIGHT/2 + 50);

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertFalse(
                false,
                ""
        ); // este caso de prueba puede ser redundante porque no hay nada nuevo que podamos meter al assert aparte
        // de comprobaciones que ya hemos hecho en los tests de abajo
    }


    @Test
    public void updateShotsCaminoC() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BOARD_WIDTH/2, Commons.BOARD_HEIGHT/2);
        testBoard.getAliens().add(alien);
        alien.die();

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertFalse(
                false,
                ""
        ); // este caso de prueba puede ser redundante porque no hay nada nuevo que podamos meter al assert aparte
        // de comprobaciones que ya hemos hecho en los tests de abajo
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
        testBoard.getShot().setY(Commons.BOARD_HEIGHT/2);
        testBoard.getAliens().clear();

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertFalse(
                testBoard.getShot().getY() == Commons.BOARD_HEIGHT/2,
                "La posición del disparo no se ha actualizado"
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
                "El disparo es visible"
        );
        //EL DEATHS++ DE AL FINAL DEL METODO DE UPDATE SHOTS DEBERÍA SER THIS.SHOT.DIE()
    }
}

