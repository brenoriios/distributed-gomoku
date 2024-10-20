package view.game.button;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelButton extends JPanel {
    private final BufferedImage background;
    final Button innerButton;

    public PanelButton(BufferedImage background, Dimension size) {
        this.setLayout(new BorderLayout());
        this.background = background;
        this.setPreferredSize(size);

        this.innerButton = new Button();
        this.add(this.innerButton, BorderLayout.CENTER);
    }

    public PanelButton(BufferedImage background) {
        this(background, new Dimension(50, 50));
    }

    public Button getButton(){
        return this.innerButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}