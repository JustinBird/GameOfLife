import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardMouse extends MouseAdapter {

    Board b;

    public BoardMouse(Board b) {
        this.b = b;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int xCoord = e.getX() / 10;
        int yCoord = e.getY() / 10;

        if (b.isAlive(xCoord, yCoord)) {
            b.setDead(xCoord, yCoord);
        } else {
            b.setAlive(xCoord, yCoord);
        }

        b.repaint();
    }
}
