import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Bouton extends JButton implements MouseListener {
    private String name;
    private Color color;

    Bouton(String str) {
        super(str);
        name = str;
        color = Color.BLUE;
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp = new GradientPaint(0, 0, color, 0, 20, Color.CYAN);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.WHITE);
        g2d.drawString(name, this.getWidth() / 2 - (this.getWidth() / 2 / 4), (this.getHeight() / 2) + 5);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        changeColor(Color.RED);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        changeColor(Color.BLUE);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        changeColor(Color.YELLOW);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        changeColor(Color.BLUE);
    }

    private void changeColor(Color C) {
        color = C;
        repaint();
    }
}
