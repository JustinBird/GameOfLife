import javax.swing.*;

public class main {

    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame("Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 900);
        Board b = new Board(100, 100);
        b.setBoard(0, 0);
        b.setBoard(3, 4);
        f.getContentPane().add(b);
        f.pack();
        f.setVisible(true);
    }
}