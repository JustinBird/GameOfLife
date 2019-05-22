import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    /**
     * Assert all squares have zero neighbors on an empty board.
     */
    @Test
    public void CountNeighborsEmpty() {

        Board b = new Board(5, 5);

        for (int i = 0; i < b.getBoardHeight(); i++) {
            for (int j = 0; j < b.getBoardWidth(); j++) {
                assertEquals(b.countNeighbors(j, i), 0);
            }
        }
    }

    /**
     * Assert a center square has eight neighbors on a full board.
     */
    @Test
    public void CounterNeighborsFull() {

        Board b = new Board(5, 5);
        for (int i = 0; i < b.getBoardHeight(); i++) {
            for (int j = 0; j < b.getBoardWidth(); j++) {
                b.setAlive(i, j);
            }
        }

        assertEquals(b.countNeighbors(2, 2), 8);
    }
}
