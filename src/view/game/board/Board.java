package view.game.board;

import env.Env;
import view.game.button.Button;
import view.game.button.PanelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Board extends JPanel {
    private final Button[][] buttons = new Button[15][15];

    public Board() {
        this.setLayout(new GridLayout(15, 15));

        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                PanelButton panelButton = new PanelButton(Env.cellBackground, new Dimension(40, 40));
                this.buttons[x][y] = panelButton.getButton();
                this.add(panelButton);
            }
        }
    }

    public void updateBoard(String[][] board) {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                Button button = this.buttons[x][y];
                if (!Objects.equals(button.getText(), board[x][y])) {
                    setButtonIcon(board[x][y], button);
                    button.repaint();
                }
            }
        }
    }

    public void setButtonIcon(String pieceColor, Button button) {
        BufferedImage image = null;

        if (Objects.equals(pieceColor, Env.pieceBlack)) {
            image = Env.greenPiece;
        }

        if (Objects.equals(pieceColor, Env.pieceWhite)) {
            image = Env.redPiece;
        }

        if (image == null || Objects.equals(pieceColor, Env.emptyCell)) {
            return;
        }

        button.setIcon(new ImageIcon(image));
    }

    public Button[][] getButtons() {
        return this.buttons;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        Board b = new Board();
        f.add(b);
        f.pack();
        f.setVisible(true);
    }
}