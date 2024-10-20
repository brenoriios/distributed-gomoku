import engine.ClientEngine;
import engine.IGomoku;
import view.GUI;

import java.util.Objects;

public class Client {
    private String currentPlayer;
    private final GUI gui;
    private final ClientEngine engine;

    private Thread updater;

    public Client(GUI gui, ClientEngine engine){
        this.gui = gui;
        this.engine = engine;

        this.setupMenuListeners();
        this.setupBoardListeners();
    }

    public void setupMenuListeners() {
        this.gui.getMenu().getConnectButton().addActionListener(_ -> {
            if(this.engine.connect(this.gui.getMenu().getPlayerName())){
                this.gui.getMenu().next();
                this.checkGameStartedThread();
            }
        });
    }

    public void setupBoardListeners(){
        for(int row = 0; row < IGomoku.maxCols; row++){
            for(int col = 0; col < IGomoku.maxRows; col++) {
                int finalRow = row;
                int finalCol = col;
                this.gui.getGame().getBoard().getButtons()[row][col].addActionListener(_ -> {
                    System.out.println("row: " + finalRow + " | col: " + finalCol);
                    this.engine.placePiece(finalRow, finalCol);
                });
            }
        }
    }

    public void checkGameStartedThread(){
        new Thread(() -> {
            boolean gameStarted;
            do {
                gameStarted = this.engine.isGameStarted();
            } while (!gameStarted);

            startUpdaterThread();
            this.gui.showBoard(this.engine.getPlayer(), this.engine.getOpponent());
        }).start();
    }

    public void startUpdaterThread(){
        this.updater = new Thread(() -> {
            try {
                while (true){
                    Thread.sleep(1000);
                    updateBoard();
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        });

        updater.start();
    }

    public void updateBoard(){
        String winner = this.engine.getWinner();
        if (!Objects.equals(winner, ""))  {
            this.gui.showWinner(winner);
            this.updater.interrupt();
            return;
        }

        String newCurrentPlayer = this.engine.getCurrentPlayer();
        if (!Objects.equals(this.currentPlayer, newCurrentPlayer)){
            this.currentPlayer = newCurrentPlayer;
            this.gui.getGame().updateGame(engine.getBoard(), engine.getCurrentPlayer());
        }
    }

    public void run(){
        this.gui.init();
    }

    public static void main(String[] args) {
        GUI ui = new GUI();
        ClientEngine cl = new ClientEngine();
        Client ct = new Client(ui, cl);

        ct.run();
    }
}
