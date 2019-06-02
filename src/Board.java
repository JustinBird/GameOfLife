import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Board extends JPanel {
    
    private int boardHeight;
    private int boardWidth;
    private boolean[][] board;

    private boolean running;
    private ScheduledFuture<?> future;

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public boolean isRunning() {
        return running;
    }

    /**
     * Board constructor. Initializes class variables and adds mouse listener.
     * @param width Width of board.
     * @param height Height of board.
     */
    public Board(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        board = new boolean[height][width];

        BoardMouse bm = new BoardMouse(this);
        addMouseListener(bm);
    }

    /**
     * Returns whether a cell at position (x, y) is alive.
     * @param x X Position.
     * @param y Y Position.
     * @return True if cell at (x, y) is alive.
     */
    public boolean isAlive(int x, int y) {
        return board[y][x];
    }

    /**
     * Sets a position on the board to alive.
     * @param x X Position.
     * @param y Y position.
     */
    public void setAlive(int x, int y) {
        board[y][x] = true;
    }

    /**
     * Sets a position on the board to dead.
     * @param x X Position.
     * @param y Y position.
     */
    public void setDead(int x, int y) {
        board[y][x] = false;
    }

    /**
     * Counts the number of neighbors a square has. Contributions from:
     * https://codereview.stackexchange.com/questions/62160/checking-for-neighbours-more-elegantly-in-conways-game-of-life
     * @param x X position.
     * @param y Y position.
     * @return Number of neighbors.
     */
    public int countNeighbors(int x , int y) {

        int[][] neighborOffset = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1,  0},          {1,  0},
                {-1,  1}, {0,  1}, {1,  1}
        };

        int neighbors = 0;

        for (int[] offset : neighborOffset) {
            if (x + offset[0] >= 0 && x + offset[0] < boardWidth) {
                if (y + offset[1] >= 0 && y + offset[1] < boardHeight) {
                    if (board[y + offset[1]][x + offset[0]]) {
                        neighbors += 1;
                    }
                }
            }
        }

        return neighbors;
    }

    public boolean updateCell(int x, int y) {

        int neighbors = countNeighbors(x, y);

        if (board[y][x]) {

            // Rule 1: Any live cell with fewer than two live neighbours dies.
            if (neighbors < 2) {
                return false;

            // Rule 2: Any live cell with more than three live neighbours dies.
            } else if (neighbors > 3) {
                return false;

            // Rule 3: Any live cell with two or three live neighbours lives, unchanged, to the next generation.
            } else {
                return true;
            }

        } else {

            if (neighbors == 3) {
                // Rule 4: Any dead cell with exactly three live neighbours will come to life.
                return true;
            } else {
                return false;
            }
        }
    }

    public void updateBoard() {

        boolean[][] newBoard = new boolean[boardHeight][boardWidth];

        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                newBoard[y][x] = updateCell(x, y);
            }
        }

        board = newBoard;
        repaint();
    }

    public void startUpdates() {

        if (!isRunning()) {
            ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
            future = schedule.scheduleAtFixedRate(() -> {
                updateBoard();
            }, 0, 1000L, TimeUnit.MILLISECONDS);
            running = true;
        }
    }

    public void stopUpdates() {

        if (isRunning()) {
            future.cancel(true);
            running = false;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(boardWidth * 10, boardHeight * 10);
    }

    /**
     * Draws board as a JPanel.
     * @param g Graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {

                if (board[i][j]) {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * 10, i * 10, 10, 10);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(j * 10, i * 10, 10, 10);
                }
            }
        }
    }
}
