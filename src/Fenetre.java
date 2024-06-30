import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {
    private Boule boule = new Boule();
    private JButton button = new JButton("Go");
    private JButton button2 = new JButton("Stop");
    private JPanel container = new JPanel();
    private JLabel label = new JLabel("JLabel");
    private int compteur = 0, x, y;
    private boolean animated = true, backX, backY;
    private Thread t;
    Fenetre() {
        setTitle("Animation");
        setSize(400, 400);
        setLocationRelativeTo(null);
        button.addActionListener(new ButtonListener());
        button2.addActionListener(new Button2Listener());
        button.setEnabled(false);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setForeground(Color.ORANGE);
        label.setHorizontalAlignment(label.CENTER);
        container.setLayout(new BorderLayout());
        container.add(label, BorderLayout.NORTH);
        container.add(boule, BorderLayout.CENTER);
        JPanel south = new JPanel();
        south.add(button, BorderLayout.SOUTH);
        south.add(button2, BorderLayout.SOUTH);
        container.add(south ,BorderLayout.SOUTH);
        setContentPane(container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        go();
    }

    private void go() {
        x = boule.getPosX();
        y = boule.getPosY();
        while(this.animated) {
            if (x < 1) backX = false;
            if (x > boule.getWidth() - 50) backX = true;

            if (y < 1) backY = false;
            if (y > boule.getHeight() - 50) backY = true;

            if (!backX) boule.setPosX(++x);
            else boule.setPosX(--x);

            if (!backY) boule.setPosY(++y);
            else boule.setPosY(--y);
            boule.repaint();
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            animated = true;
            t = new Thread(new Animation());
            t.start();
            button.setEnabled(false);
            button2.setEnabled(true);
        }
    }

    private class Button2Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            animated = false;
            button.setEnabled(true);
            button2.setEnabled(false);
        }
    }

    private class Animation implements Runnable {
        public void run() {
            go();
        }
    }
}
