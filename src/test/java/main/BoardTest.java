package main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void creacionBoardTest() {
        Board board = new Board();

        assertNotNull(board, "Board should be created successfully");
    }
}
