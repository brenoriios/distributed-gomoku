package view.board.topbar;

import engine.Player;
import env.Env;
import view.board.Board;
import view.board.topbar.playercard.PlayerCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Topbar extends JPanel {
    private final PlayerCard playerOne;
    private final PlayerCard playerTwo;

    public Topbar(Player playerOne, Player playerTwo, String currentPlayer){
        this.setBackground(Color.black);

        JLabel versusImage = new JLabel();
        versusImage.setIcon(new ImageIcon(loadImage("../../assets/vs.png")));
        versusImage.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.playerOne = new PlayerCard(getPieceColorResourceImage(playerOne.getPieceColor()), playerOne.getName() + " (Você)", isCurrentPlayer(playerOne, currentPlayer));
        this.playerTwo = new PlayerCard(getPieceColorResourceImage(playerTwo.getPieceColor()), playerTwo.getName(), isCurrentPlayer(playerTwo, currentPlayer));

        this.add(this.playerOne);
        this.add(versusImage);
        this.add(this.playerTwo);
    }

    public Icon getPieceColorResourceImage(String pieceColor){
        String resourcePath = "../" + Env.blackPiecePath;

        if (pieceColor == Env.pieceWhite){
            resourcePath = "../" + Env.whitePiecePath;
        }

        BufferedImage img = loadImage(resourcePath);

        return new ImageIcon(img);
    }

    public String isCurrentPlayer(Player player, String currentPlayer){
        if(player.getId() == currentPlayer) return "Jogando...";

        return "Aguardando...";
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(Topbar.class.getResource(imagePath));
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Player playerOne = new Player("1234", "Player 1");
        Player playerTwo = new Player("4321", "Player 2");

        playerOne.setPieceColor(Env.pieceWhite);
        playerTwo.setPieceColor(Env.pieceBlack);

        Topbar t = new Topbar(
                playerOne,
                playerTwo,
                "1234"
        );
        Board b = new Board();
        JFrame f = new JFrame();
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.add(t);
        f.add(b);
        f.pack();
        f.setVisible(true);
    }
}
