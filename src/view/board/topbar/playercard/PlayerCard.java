package view.board.topbar.playercard;

import javax.swing.*;
import java.awt.*;

public class PlayerCard extends JPanel {
    PlayerInfo playerInfo;
    JLabel statusLabel;

    public PlayerCard(Icon pieceColor, String playerName, String status){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0,0,0,0));

        this.playerInfo = new PlayerInfo(pieceColor, playerName);
        this.statusLabel = new JLabel(status);
        this.statusLabel.setForeground(Color.white);

        this.add(playerInfo);
        this.add(statusLabel);
    }
}

class PlayerInfo extends JPanel {
    JLabel pieceColor;
    JLabel playerName;

    public PlayerInfo(Icon pieceColor, String playerName){
        this.setBackground(new Color(0,0,0,0));
        this.pieceColor = new JLabel();
        this.playerName = new JLabel(playerName);

        this.playerName.setForeground(Color.white);

        this.pieceColor.setIcon(pieceColor);

        this.add(this.pieceColor);
        this.add(this.playerName);
    }
}