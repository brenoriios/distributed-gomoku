package view;

import env.Env;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BoardPanel extends JPanel {
    BufferedImage backgroundImage;

    public BoardPanel(){
        this.backgroundImage = loadImage();
        this.setOpaque(false);
        this.setLayout(new GridLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(this.backgroundImage, -25, -25, this.getWidth(), this.getHeight(), this);
    }

    private BufferedImage loadImage() {
        try {
            return ImageIO.read(BoardPanel.class.getResource(Env.backgroundPath));
        } catch (IOException e) {
            return null;
        }
    }
}
