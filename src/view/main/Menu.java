package view.main;

import engine.IGomoku;
import env.Env;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Menu extends JFrame {
    MenuContentPanel contentPanel;
    public final JLabel startGameLabel;
    public final JTextField nameInput;
    public final JButton connect;

    public Menu(){
        this.contentPanel = new MenuContentPanel();
        this.setContentPane(this.contentPanel);
        this.setLayout(new BoxLayout(this.contentPanel, BoxLayout.Y_AXIS));
        this.contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.startGameLabel = new JLabel("Começar o Jogo");
        this.nameInput = new JTextField();
        this.connect = new JButton("Conectar");

        this.contentPanel.add(this.startGameLabel);
        this.contentPanel.add(this.nameInput);
        this.contentPanel.add(this.connect);

        this.pack();
    }
}

class MenuContentPanel extends JPanel {
    public MenuContentPanel(){
        super();
    }
}