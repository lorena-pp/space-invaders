package main;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class BoardTest {

    @Test
    public void creacionBoardTest() {
        Board board = new Board();

        assertNotNull("Board should be created successfully", board);
    }
}
