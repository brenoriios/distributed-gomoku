package view;

import javax.swing.*;
import java.awt.*;
public class Button extends JButton {
    public Button(){
        super();
        setOpaque(false);
        this.applyStyles();
    }

    public void applyStyles(){
        this.setBackground(new Color(0, 0, 0, 0));
//        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25 ,25));
        this.setPreferredSize(new Dimension(50, 50));
    }
}