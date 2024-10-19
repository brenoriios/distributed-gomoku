package view.game.board;

import engine.Player;
import env.Env;
import view.game.button.Button;
import view.game.button.PanelButton;
import view.game.topbar.Topbar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Board extends JPanel {
    public final BufferedImage blackPiece = loadImage(Env.blackPiecePath);
    public final BufferedImage whitePiece = loadImage(Env.whitePiecePath);
    public final BufferedImage btnBackground = loadImage(Env.cellBackground);

    private final Button[][] buttons = new Button[15][15];

    public Board() {
        this.setLayout(new GridLayout(15, 15));

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                PanelButton panelButton = new PanelButton(this.btnBackground, new Dimension(40, 40));
                this.buttons[x][y] = panelButton.getButton();
                this.add(panelButton);
            }
        }
    }

    public void updateBoard(String[][] board){
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                Button button = this.buttons[x][y];
                if (!Objects.equals(button.getText(), board[x][y])){
                    setButtonIcon(board[x][y], button);
                    button.repaint();
                }
            }
        }
    }

    public void setButtonIcon(String pieceColor, Button button) {
        BufferedImage image = null;

        if(Objects.equals(pieceColor, Env.pieceBlack)) {
            image = this.blackPiece;
        }

        if(Objects.equals(pieceColor, Env.pieceWhite)) {
            image = this.whitePiece;
        }

        if(image == null || Objects.equals(pieceColor, Env.emptyCell)) {
            return;
        }

        button.setIcon(new ImageIcon(image));
    }

    public Button[][] getButtons(){
        return this.buttons;
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(Board.class.getResource(imagePath));
        } catch (IOException e) {
            return null;
        }
    }
}