package view.game.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {

    public Button(Dimension size) {
        setOpaque(false);
        this.setPreferredSize(size);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0, 0, 0, 0)));

        addMouseListener(this);
    }

    public Button() {
        this(new Dimension(25, 25));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0, 0, 0, 0)));
    }
}