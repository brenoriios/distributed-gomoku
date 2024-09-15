package view;

import env.Env;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Button extends JButton {
    BufferedImage bg;
    public Button(BufferedImage bg){
//        setOpaque(false);
        this.bg = bg;
        this.applyStyles();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.bg, 0, 0, this);
    }

    public void applyStyles(){
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setPreferredSize(new Dimension(50, 50));
    }
}