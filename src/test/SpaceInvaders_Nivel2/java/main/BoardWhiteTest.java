package main;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardWhiteTest {
    @Test
    public void update_CaminoA() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Board testBoard = new Board();
        testBoard.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);

        Method updateMethod = Board.class.getDeclaredMethod("update");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertEquals("Game won!", testBoard.getMessage());
    }

    @Test
    public void update_CaminoB() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY + 1);

        Method updateMethod = Board.class.getDeclaredMethod("update");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertEquals("Game Over", testBoard.getMessage());
    }


    @Test
    public void updateShots_CaminoA() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BOARD_WIDTH / 2, Commons.BOARD_HEIGHT / 2);
        testBoard.getAliens().add(alien);
        testBoard.getShot().setX(Commons.BOARD_WIDTH / 2);
        testBoard.getShot().setY(Commons.BOARD_HEIGHT / 2);
        int prevdeaths = testBoard.getDeaths();

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertTrue(alien.isDying(), "El alien no ha sido interceptado por el disparo");
        assertEquals(prevdeaths + 1, testBoard.getDeaths(), "El numero de muertes se ha quedado igual");
        assertFalse(testBoard.getShot().isVisible(), "El disparo sigue siendo visible");
    }

    @Test
    public void updateShots_CaminoB() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BOARD_WIDTH / 2 - 50, Commons.BOARD_HEIGHT / 2 - 50);
        testBoard.getAliens().add(alien);
        testBoard.getShot().setX(Commons.BOARD_WIDTH / 2 + 50);
        testBoard.getShot().setY(Commons.BOARD_HEIGHT / 2 + 50);

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertFalse(
                testBoard.getShot().isVisible(),
                "El disparo sigue dentro de los límites del board"
        );
    }


    @Test
    public void updateShots_CaminoC() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BOARD_WIDTH / 2, Commons.BOARD_HEIGHT / 2);
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
    public void updateShots_CaminoD() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
    public void updateShots_CaminoE() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        testBoard.getShot().setY(Commons.BOARD_HEIGHT / 2);
        testBoard.getAliens().clear();

        Method updateMethod = Board.class.getDeclaredMethod("update_shots");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertFalse(
                testBoard.getShot().getY() == Commons.BOARD_HEIGHT / 2,
                "La posición del disparo no se ha actualizado"
        );
    }

    @Test
    public void updateShots_CaminoF() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
    public void updateAliens_CaminoA() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        int dirInicial = testBoard.getDirection();
        testBoard.getAliens().clear();

        Method updateMethod = Board.class.getDeclaredMethod("update_aliens");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertEquals(dirInicial, testBoard.getDirection(), "La dirección ha cambiado de valor");
    }

    @Test
    public void updateAliens_CaminoB() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        int dirInicial = testBoard.getDirection();
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BORDER_LEFT, Commons.BOARD_HEIGHT / 2);
        testBoard.getAliens().add(alien);
        alien.die();

        Method updateMethod = Board.class.getDeclaredMethod("update_aliens");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertEquals(dirInicial, testBoard.getDirection(), "La dirección ha cambiado de valor");
    }

    @Test
    public void updateAliens_CaminoD() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board testBoard = new Board();
        int dirInicial = -1;
        testBoard.setDirection(dirInicial);
        testBoard.getAliens().clear();
        var alien = new Alien(Commons.BOARD_WIDTH / 2, Commons.BOARD_HEIGHT / 2);
        testBoard.getAliens().add(alien);
        alien.die();

        Method updateMethod = Board.class.getDeclaredMethod("update_aliens");
        updateMethod.setAccessible(true);
        updateMethod.invoke(testBoard);

        assertNotEquals(dirInicial, testBoard.getDirection(), "La dirección es igual a la inicial");
    }

    @Test
    public void updateAliens_CaminoE() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
    public void updateAliens_CaminoF() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
    public void updateBomb_CaminoA() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board board = new Board();

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        alien.die();
        board.setAliens(List.of(alien));

        Player player = new Player();
        board.setPlayer(player);

        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true);

        Method updateMethod = Board.class.getDeclaredMethod("update_bomb");
        updateMethod.setAccessible(true);
        updateMethod.invoke(board);

        assertTrue(bomb.isDestroyed());
        assertFalse(player.isDying());
    }

    @Test
    public void updateBomb_CaminoB() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board board = new Board();

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        board.setAliens(List.of(alien));

        Player player = new Player();
        player.die();
        board.setPlayer(player);

        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true);

        Method updateMethod = Board.class.getDeclaredMethod("update_bomb");
        updateMethod.setAccessible(true);

        int intentos = 0;
        while (bomb.isDestroyed() && intentos < 200) {
            updateMethod.invoke(board);
            intentos++;
        }

        assertFalse(bomb.isDestroyed());
        assertEquals(alien.getX(), bomb.getX());
    }

    @Test
    public void updateBomb_CaminoC() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board board = new Board();

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        alien.die();
        board.setAliens(List.of(alien));

        Player player = new Player();
        board.setPlayer(player);

        Alien.Bomb bomb = alien.getBomb();
        bomb.setX(alien.getX());
        bomb.setY(alien.getY());
        bomb.setDestroyed(false);

        Method updateMethod = Board.class.getDeclaredMethod("update_bomb");
        updateMethod.setAccessible(true);
        updateMethod.invoke(board);

        assertFalse(bomb.isDestroyed());
        assertFalse(player.isDying());
    }

    @Test
    public void updateBomb_CaminoD() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board board = new Board();

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        alien.die();
        board.setAliens(List.of(alien));

        Player player = new Player();
        board.setPlayer(player);

        Alien.Bomb bomb = alien.getBomb();
        bomb.setX(player.getX());
        bomb.setY(player.getY());
        bomb.setDestroyed(false);

        Method updateMethod = Board.class.getDeclaredMethod("update_bomb");
        updateMethod.setAccessible(true);
        updateMethod.invoke(board);

        assertTrue(bomb.isDestroyed());
        assertTrue(player.isDying());
    }

    @Test
    public void updateBomb_CaminoE() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board board = new Board();

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        alien.die();
        board.setAliens(List.of(alien));

        Player player = new Player();
        player.die();
        board.setPlayer(player);

        int yInicial = 100;
        Alien.Bomb bomb = alien.getBomb();
        bomb.setX(alien.getX());
        bomb.setY(yInicial);
        bomb.setDestroyed(false);

        Method updateMethod = Board.class.getDeclaredMethod("update_bomb");
        updateMethod.setAccessible(true);
        updateMethod.invoke(board);

        assertFalse(bomb.isDestroyed());
        assertEquals(yInicial + Commons.BOMB_SPEED, bomb.getY());
    }

    @Test
    public void updateBomb_CaminoF() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board board = new Board();

        Alien alien = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        alien.die();
        board.setAliens(List.of(alien));

        Player player = new Player();
        player.die();
        board.setPlayer(player);

        Alien.Bomb bomb = alien.getBomb();
        bomb.setX(alien.getX());
        bomb.setY((Commons.GROUND - Commons.BOMB_HEIGHT) * Commons.BOMB_SPEED + 10); // Valor alto para entrar en nodo 12
        bomb.setDestroyed(false);

        Method updateMethod = Board.class.getDeclaredMethod("update_bomb");
        updateMethod.setAccessible(true);
        updateMethod.invoke(board);

        assertTrue(bomb.isDestroyed());
    }

    @Test
    public void updateBomb_CaminoG() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Board board = new Board();

        Alien alien1 = new Alien(Commons.ALIEN_INIT_X, Commons.ALIEN_INIT_Y);
        alien1.die();
        alien1.getBomb().setDestroyed(true);

        Alien alien2 = new Alien(Commons.ALIEN_INIT_X + 20, Commons.ALIEN_INIT_Y);
        alien2.die();
        alien2.getBomb().setDestroyed(true);

        board.setAliens(List.of(alien1, alien2));

        Player player = new Player();
        board.setPlayer(player);

        Method updateMethod = Board.class.getDeclaredMethod("update_bomb");
        updateMethod.setAccessible(true);
        updateMethod.invoke(board);

        assertTrue(alien1.getBomb().isDestroyed());
        assertTrue(alien2.getBomb().isDestroyed());
    }

    @Test
    public void gameInit_CaminoA() {
        // camino imposible
    }

    @Test
    public void gameInit_CaminoB() {
        // camino imposible
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

