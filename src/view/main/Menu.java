package view.main;

import engine.IGomoku;
import env.Env;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Menu extends JFrame {
    public final JLabel startGameLabel;
    public final JTextField nameInput;
    public final JButton connect;

    public Menu(){
        MenuContentPanel contentPanel = new MenuContentPanel();
        this.setContentPane(contentPanel);
        this.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.startGameLabel = new JLabel("Começar o Jogo");
        this.nameInput = new JTextField();
        this.connect = new JButton("Conectar");

        contentPanel.add(this.startGameLabel);
        contentPanel.add(this.nameInput);
        contentPanel.add(this.connect);

        this.pack();
    }
}

class MenuContentPanel extends JPanel {
    public MenuContentPanel(){
        super();
    }
}