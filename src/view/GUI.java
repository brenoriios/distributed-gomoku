package view;

import engine.Client;
import view.board.Board;
import view.main.Menu;

import javax.swing.*;

public class GUI extends JFrame {
    private Menu menu;
    private Board board;

    public GUI(){
        this.menu = new Menu();
        this.board = new Board();
    }

    public void init(){
        this.showMenu();
    }

    public void showMenu(){
        this.menu.setVisible(true);
    }

    public void showBoard(){
        this.board.setVisible(true);
    }

    public Menu getMenu(){
        return this.menu;
    }

    public Board getBoard(){
        return this.board;
    }
}