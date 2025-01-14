package view.game.topbar;

import engine.Player;
import env.ClientResources;
import env.Common;
import view.game.topbar.playercard.PlayerCard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Topbar extends JPanel {
    private final PlayerCard playerOneCard;
    private final PlayerCard playerTwoCard;

    public Topbar() {
        this.setBackground(new Color(79, 34, 0));

        JLabel versusImage = new JLabel();
        versusImage.setIcon(new ImageIcon(ClientResources.versus));
        versusImage.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.playerOneCard = new PlayerCard();
        this.playerTwoCard = new PlayerCard();

        this.add(this.playerOneCard);
        this.add(versusImage);
        this.add(this.playerTwoCard);
    }

    public Icon getPieceColorResourceImage(String pieceColor) {
        BufferedImage image = ClientResources.greenPiece;

        if (Objects.equals(pieceColor, Common.pieceWhite)) {
            image = ClientResources.redPiece;
        }

        return new ImageIcon(image);
    }

    public void updatePlayers(Player playerOne, Player playerTwo) {
        this.playerOneCard.updatePlayerInfo(getPieceColorResourceImage(playerOne.getPieceColor()), playerOne.getName() + " (Você)");
        this.playerTwoCard.updatePlayerInfo(getPieceColorResourceImage(playerTwo.getPieceColor()), playerTwo.getName());
    }

    public void updateStatus(Player player, String currentPlayer) {
        if (isCurrentPlayer(player, currentPlayer)) {
            this.playerOneCard.updateStatus("Jogando...");
            this.playerTwoCard.updateStatus("Aguardando...");
        } else {
            this.playerOneCard.updateStatus("Aguardando...");
            this.playerTwoCard.updateStatus("Jogando...");
        }
    }

    public boolean isCurrentPlayer(Player player, String currentPlayer) {
        return Objects.equals(player.getId(), currentPlayer);
    }
}
