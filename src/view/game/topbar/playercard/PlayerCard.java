package view.game.topbar.playercard;

import javax.swing.*;
import java.awt.*;

public class PlayerCard extends JPanel {
    PlayerInfo playerInfo;
    JLabel statusLabel;

    public PlayerCard(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0,0,0,0));

        this.playerInfo = new PlayerInfo();
        this.statusLabel = new JLabel();
        this.statusLabel.setForeground(Color.white);

        this.add(playerInfo);
        this.add(statusLabel);
    }

    public void updatePlayerInfo(Icon pieceColor, String name){
        this.playerInfo.updatePlayerInfo(pieceColor, name);
    }

    public void updateStatus(String text){
        this.statusLabel.setText(text);
    }
}

class PlayerInfo extends JPanel {
    JLabel pieceColor;
    JLabel playerName;

    public PlayerInfo(){
        this.setBackground(new Color(0,0,0,0));
        this.pieceColor = new JLabel();
        this.playerName = new JLabel();

        this.playerName.setForeground(Color.white);

        this.add(this.pieceColor);
        this.add(this.playerName);
    }

    public void updatePlayerInfo(Icon icon, String name){
        this.pieceColor.setIcon(icon);
        this.playerName.setText(name);
    }
}