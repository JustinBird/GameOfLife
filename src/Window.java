import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

    private Board b;

    public Window() {
        JFrame f = new JFrame("Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 900);
        b = new Board(100, 100);
        b.setAlive(0, 0);
        b.setAlive(3, 4);
        f.getContentPane().add(b);
        f.setJMenuBar(new WindowMenuBar());
        f.pack();
        f.setVisible(true);
        b.updateBoard();
    }

    private class WindowMenuBar extends JMenuBar {

        public WindowMenuBar() {
            super();

            JMenu RunMenu = new JMenu("Run");

            JMenuItem step = new JMenuItem("Step");
            step.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    b.updateBoard();
                }
            });
            RunMenu.add(step);

            add(RunMenu);
        }
    }
}
