package view.endgame;

import env.Env;

import javax.swing.*;
import java.awt.*;

public class WinnerScreen extends JFrame {
    private JLabel winner;
    private final JPanel content;

    public WinnerScreen() {
        WinnerScreenContentPane contentPane = new WinnerScreenContentPane();
        contentPane.setLayout(new GridBagLayout());
        this.setContentPane(contentPane);
        this.setMinimumSize(new Dimension(500, 500));

        this.content = new JPanel();
        this.content.setBackground(new Color(255, 255, 255));
        this.content.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        this.winner = new JLabel("Breno Rios", SwingConstants.CENTER);

        content.add(this.winner);

        contentPane.add(content);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        WinnerScreen t = new WinnerScreen();
        t.setVisible(true);
    }
}

class WinnerScreenContentPane extends JPanel {
    public WinnerScreenContentPane() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Env.menuBg, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}