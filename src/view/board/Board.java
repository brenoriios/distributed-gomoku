package view.board;

import env.Env;
import view.board.button.Button;
import view.board.button.PanelButton;
import view.board.topbar.Topbar;

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
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                PanelButton panelButton = new PanelButton(this.btnBackground, new Dimension(40, 40));
                this.buttons[x][y] = panelButton.getButton();
                this.add(panelButton);
            }
        }
    }

    public void update(String[][] board){
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

    public void setButtonIcon(String pieceCollor, Button button) {
        BufferedImage image = null;

        if(Objects.equals(pieceCollor, Env.pieceBlack)) {
            image = this.blackPiece;
        }

        if(Objects.equals(pieceCollor, Env.pieceWhite)) {
            image = this.whitePiece;
        }

        if(image == null || Objects.equals(pieceCollor, Env.emptyCell)) {
            return;
        }

        button.setIcon(new ImageIcon(image));
    }

    public Button[][] getButtons(){
        return this.buttons;
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(ContentPanel.class.getResource(imagePath));
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Board b = new Board();
        b.setVisible(true);
    }
}

class ContentPanel extends JPanel {
    public ContentPanel(){
        this.setLayout(new GridLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}