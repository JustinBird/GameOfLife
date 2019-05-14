import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {


    private int boardHeight;
    private int boardWidth;
    private boolean[][] board;


    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public Board() {

    }

    public Board(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        board = new boolean[height][width];
    }

    public void setBoard(int x, int y) {
        board[y][x] = !board[y][x];
    }

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
        return new Dimension(boardWidth * 100, boardHeight * 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {

                if (board[i][j]) {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * 100, i * 100, 100, 100);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(j * 100, i * 100, 100, 100);
                }
            }
        }
    }
}
