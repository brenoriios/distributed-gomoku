package view;

import engine.Player;
import view.endgame.WinnerScreen;
import view.game.Game;
import view.menu.Menu;

import javax.swing.*;

public class GUI extends JFrame {
    private final Menu menu;
    private final Game game;
    private final WinnerScreen endGame;

    public GUI(){
        this.menu = new Menu();
        this.game = new Game();
        this.endGame = new WinnerScreen();
    }

    public void init(){
        this.showMenu();
    }

    public void showMenu(){
        this.menu.setVisible(true);
    }

    public void showBoard(Player playerOne, Player playerTwo){
        if (playerOne == null) playerOne = new Player();
        if (playerTwo == null) playerTwo = new Player();

        this.menu.setVisible(false);
        this.game.setPlayers(playerOne, playerTwo);
        this.game.setVisible(true);
    }

    public void showWinner(String winner){
        this.game.setVisible(false);
        this.endGame.setVisible(true, winner);
    }

    public Menu getMenu(){
        return this.menu;
    }

    public Game getGame(){
        return this.game;
    }
}