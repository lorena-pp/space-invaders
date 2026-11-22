package main;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class BoardWhiteTest {
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
        int prevdeaths = testBoard.getDeaths();

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertTrue(alien.isDying(), "El alien no ha sido interceptado por el disparo");
        assertEquals(prevdeaths + 1,  testBoard.getDeaths(), "El numero de muertes se ha quedado igual");
        assertFalse(testBoard.getShot().isVisible(), "El disparo sigue siendo visible");
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
                testBoard.getShot().isVisible(),
                "El disparo sigue dentro de los límites del board"
        );
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
                testBoard.getShot().isVisible(),
                "El disparo sigue dentro de los límites del board"
        );
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

    }


/* ERRORES ENCONTRADOS EN UODATE_SHOTS DE PRIMERAS:
   1 - EL DEATHS++ DE AL FINAL DEL METODO DE UPDATE SHOTS DEBERÍA SER THIS.SHOT.DIE()
 */

/* ERRORES ENCONTRADOS EN UODATE_ALIEN DE PRIMERAS:
   1 - EN EL SEGUNDO IF, SE HACE UNA COMPROBACION "X != Commons.BORDER_LEFT" CUANDO DEBERÍAS SER <=.
   2 - EN EL ÚLTIMO IF DEL METODO, SE HACE LA COMPROBACION "y > Commons.GROUND * Commons.ALIEN_HEIGHT"
        CUANDO ESAS CONSTANTES DEBERÍAN RESTARSE.
 */

    @Test
    public void updateAliensCaminoA() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        int dirInicial = testBoard.getDirection();
        testBoard.getAliens().clear();

        Method updateMethod = Board.class.getDeclaredMethod("update_aliens");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertEquals(dirInicial, testBoard.getDirection(), "La dirección ha cambiado de valor");
    }

    @Test
    public void updateAliensCaminoB() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        int dirInicial = testBoard.getDirection();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BORDER_LEFT, Commons.BOARD_HEIGHT/2);
        testBoard.getAliens().add(alien);
        alien.die();

        Method updateMethod = Board.class.getDeclaredMethod("update_aliens");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertEquals(dirInicial, testBoard.getDirection(), "La dirección ha cambiado de valor");
    }

    @Test
    public void updateAliensCaminoD() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        int dirInicial = -1;
        testBoard.setDirection(dirInicial);
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BOARD_WIDTH/2, Commons.BOARD_HEIGHT/2);
        testBoard.getAliens().add(alien);
        alien.die();

        Method updateMethod = Board.class.getDeclaredMethod("update_aliens");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertNotEquals(dirInicial, testBoard.getDirection(), "La dirección es igual a la inicial");
    }

    @Test
    public void updateAliensCaminoE() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BOARD_WIDTH / 2, Commons.GROUND - Commons.ALIEN_HEIGHT);
        testBoard.getAliens().add(alien);

        Method updateMethod = Board.class.getDeclaredMethod("update_aliens");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);
        assertFalse(testBoard.isInGame(), "La partida debería continuar");
        assertNotEquals("Invasion!", testBoard.getMessage(), "La partida debería continuar");
    }

    @Test
    public void updateAliensCaminoF() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BORDER_LEFT, Commons.GROUND * Commons.ALIEN_HEIGHT + 1);
        testBoard.getAliens().add(alien);

        Method updateMethod = Board.class.getDeclaredMethod("update_aliens");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);
        assertFalse(testBoard.isInGame(), "La partida debería continuar");
        assertEquals("Invasion!", testBoard.getMessage(), "La partida debería haber terminado");
    }

    @Test
    public void updateBomb_CaminoA(){
        Board board = new Board();
        // No tengo ni idea de hacerlo
    }

    @Test
    public void gameInit_CaminoA() {
        Board board =  new Board();

        board.getAliens().clear();

        assertNotNull(board.getPlayer());
        assertNotNull(board.getShot());
    }

    @Test
    public void gameInit_CaminoB() {
        // TODO: tabla de decisión y plantear otros caminos
    }

    @Test
    public void gameInit_CaminoC() {
        Board board = new Board();

        Alien primerAlien = board.getAliens().get(0);
        assertEquals(Commons.ALIEN_INIT_X, primerAlien.getX());
        assertEquals(Commons.ALIEN_INIT_Y, primerAlien.getY());

        // ERROR: Utiliza - en lugar de + en el cálculo de la coordenada vertical
        Alien ultimoAlien = board.getAliens().get(board.getAliens().size() - 1);
        int expectedLastX = Commons.ALIEN_INIT_X + Commons.ALIEN_SEPARATOR * (Commons.ALIEN_COLUMNS - 1);
        int expectedLastY = Commons.ALIEN_INIT_Y - Commons.ALIEN_SEPARATOR * (Commons.ALIEN_ROWS - 1);
        assertEquals(expectedLastX, ultimoAlien.getX());
        assertEquals(expectedLastY, ultimoAlien.getY());

        assertEquals(Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS, board.getAliens().size());

        assertNotNull(board.getAliens());
        assertNotNull(board.getPlayer());
        assertNotNull(board.getShot());
    }

}

