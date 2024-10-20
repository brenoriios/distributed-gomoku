package view.menu;

import env.ClientResources;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private JTextField nameInput;
    private JButton connect;
    private final JPanel content;
    private final CardLayout contentLayout;

    public Menu() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        MenuContentPane contentPane = new MenuContentPane();
        contentPane.setLayout(new GridBagLayout());
        this.setContentPane(contentPane);
        this.setMinimumSize(new Dimension(500, 500));

        this.contentLayout = new CardLayout();
        this.content = new JPanel();
        this.content.setLayout(this.contentLayout);
        this.content.setBackground(new Color(255, 255, 255));
        this.content.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel connectSection = createConnectSection();
        JLabel startGameLabel = new JLabel("Aguardando o outro jogador...", SwingConstants.CENTER);

        content.add(connectSection);
        content.add(startGameLabel);

        contentPane.add(content);

        this.pack();
    }

    private JPanel createConnectSection() {
        JLabel startGameLabel = new JLabel("Digite o seu nome:", SwingConstants.CENTER);
        this.nameInput = new JTextField(20);
        this.connect = new JButton("Conectar");

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel connectPanel = new JPanel();
        connectPanel.setBackground(new Color(0, 0, 0, 0));
        connectPanel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        connectPanel.add(startGameLabel, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 0;
        gbc.gridy = 1;
        connectPanel.add(this.nameInput, gbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        connectPanel.add(this.connect, gbc);

        return connectPanel;
    }

    public void next() {
        this.contentLayout.next(this.content);
    }

    public String getPlayerName() {
        return this.nameInput.getText();
    }

    public JButton getConnectButton() {
        return this.connect;
    }
}

class MenuContentPane extends JPanel {
    public MenuContentPane() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ClientResources.menuBg, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}