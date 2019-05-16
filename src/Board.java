import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board extends JPanel implements MouseListener {


    private int boardHeight;
    private int boardWidth;
    private boolean[][] board;


    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
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

        addMouseListener(this);
    }

    /**
     * Flips a position on the board.
     * @param x X Position.
     * @param y Y position.
     */
    public void setBoard(int x, int y) {
        board[y][x] = !board[y][x];
    }

    /**
     * Debug function.
     */
    public void printBoard() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
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

    @Override
    public void mousePressed(MouseEvent e) {
        setBoard(e.getX() / 10, e.getY() / 10);
        System.out.println(countNeighbors(e.getX() / 10, e.getY() / 10));
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Hello2");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Hello3");
    }
    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Hello4");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
}
