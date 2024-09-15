package view;

import env.Env;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Board {
    public BufferedImage blackPiece;
    public BufferedImage whitePiece;
    public BufferedImage btnBackground;

    JFrame boardFrame = new JFrame("Gomoku");
    public Button[][] buttons = new Button[15][15];

    public Board() {
        this.blackPiece = loadImage(Env.blackPiecePath);
        this.whitePiece = loadImage(Env.whitePiecePath);
        this.btnBackground = loadImage("assets/cross.png");

        this.boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.boardFrame.setContentPane(new BoardPanel());

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                this.buttons[x][y] = new Button(this.btnBackground);
                this.boardFrame.getContentPane().add(this.buttons[x][y]);
            }
        }

        this.boardFrame.pack();
        this.boardFrame.setVisible(true);
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

    public void quit(){
        this.boardFrame.dispatchEvent(new WindowEvent(this.boardFrame, WindowEvent.WINDOW_CLOSING));
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(BoardPanel.class.getResource(imagePath));
        } catch (IOException e) {
            return null;
        }
    }
}
