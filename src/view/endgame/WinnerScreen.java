package view.endgame;

import env.Env;

import javax.swing.*;
import java.awt.*;

public class WinnerScreen extends JFrame {
    private final JLabel winner;

    public WinnerScreen() {
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Env.customFont);

        WinnerScreenContentPane contentPane = new WinnerScreenContentPane();
        contentPane.setLayout(new GridBagLayout());
        this.setContentPane(contentPane);
        this.setMinimumSize(new Dimension(500, 500));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(new Color(255, 255, 255, 220));
        content.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel title = new JLabel("FIM DE JOGO", SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
        title.setFont(new Font("Orbitron", Font.BOLD, 48));
        JLabel label = new JLabel("Vencedor:", SwingConstants.CENTER);
        label.setFont(new Font("Orbitron", Font.PLAIN, 24));
        this.winner = new JLabel("", SwingConstants.CENTER);
        this.winner.setFont(new Font("Orbitron", Font.PLAIN, 36));

        content.add(title);
        content.add(label);
        content.add(this.winner);

        contentPane.add(content);

        this.pack();
    }

    public void setVisible(boolean b, String winnerName) {
        this.winner.setText(winnerName);
        super.setVisible(b);
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