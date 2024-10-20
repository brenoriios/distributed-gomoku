package view.game;

import engine.Player;
import view.game.board.Board;
import view.game.topbar.Topbar;
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public Player player;

    public Board board;
    public Topbar topbar;

    public Game(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.board = new Board();
        this.topbar = new Topbar();

        this.add(this.topbar);
        this.add(this.board);
        this.pack();
    }

    public void setPlayers(Player playerOne, Player playerTwo){
        this.player = playerOne;
        this.topbar.updatePlayers(playerOne, playerTwo);
    }

    public void updateGame(String[][] board, String currentPlayer){
        this.board.updateBoard(board);
        this.topbar.updateStatus(this.player, currentPlayer);
    }

    public Board getBoard(){
        return this.board;
    }
}