package view.board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    public BoardPanel(){
        this.setLayout(new GridLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}
