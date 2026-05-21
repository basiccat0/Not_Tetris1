import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener {

    BrickLayout layout;

    public DrawPanel() {
        this.addMouseListener(this);
        layout = new BrickLayout("src/input_file");

    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] grid = layout.getGrid();
        int x = 10;
        int y = 10;
        for (int r = 0; r < 30; r++) {
            for (int c = 0; c < 40; c++) {
                Graphics2D g2 = (Graphics2D) g;
                // if this spot in the grid is set to 1, color it red
                g.drawRect(x, y, 20, 20);

                if (grid[r][c] == 1) {
                    g2.setColor(Color.RED);
                    g2.fillRect(x, y, 20, 20);
                    g2.setColor(Color.BLACK);
                }

                x += 24;
            }
            x = 10;
            y += 24;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        layout.dropOneBrick();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}