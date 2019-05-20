import javax.swing.*;

public class Window {

    public Window() {
        JFrame f = new JFrame("Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 900);
        Board b = new Board(100, 100);
        b.setBoard(0, 0);
        b.setBoard(3, 4);
        f.getContentPane().add(b);
        f.setJMenuBar(new WindowMenuBar());
        f.pack();
        f.setVisible(true);
    }

    private class WindowMenuBar extends JMenuBar {

        public WindowMenuBar() {
            super();

            JMenu RunMenu = new JMenu("Run");
            add(RunMenu);
        }
    }
}
