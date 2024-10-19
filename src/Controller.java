import engine.Client;
import engine.IGomoku;
import view.GUI;

import java.util.Objects;

public class Controller {
    private String currentPlayer;
    private final GUI view;
    private final Client model;

    private Thread updater;

    public Controller(GUI view, Client model){
        this.view = view;
        this.model = model;

        this.setupMenuListeners();
        this.setupBoardListeners();
    }

    public void setupMenuListeners() {
        this.view.getMenu().getConnectButton().addActionListener(_ -> {
            if(this.model.connect(this.view.getMenu().getPlayerName())){
                this.view.getMenu().next();
                this.checkGameStartedThread();
            }
        });
    }

    public void setupBoardListeners(){
        for(int x = 0; x < IGomoku.width; x++){
            for(int y = 0; y < IGomoku.height; y++) {
                int finalX = x;
                int finalY = y;
                this.view.getGame().getBoard().getButtons()[x][y].addActionListener(_ -> {
                    this.model.placePiece(finalX, finalY);
                });
            }
        }
    }

    public void checkGameStartedThread(){
        new Thread(() -> {
            boolean gameStarted;
            do {
                gameStarted = this.model.isGameStarted();
            } while (!gameStarted);

            startUpdaterThread();
            this.view.showBoard(this.model.getPlayer(), this.model.getOpponent());
        }).start();
    }

    public void startUpdaterThread(){
        this.updater = new Thread(() -> {
            try {
                while (true){
                    Thread.sleep(1000);
                    updateBoard();
                }
            } catch(InterruptedException _) {}
        });

        updater.start();
    }

    public void updateBoard(){
        String winner = this.model.getWinner();
        System.out.println(winner);
        if (!Objects.equals(winner, ""))  {
//            this.view.showWinner(winner);
            this.updater.interrupt();
            return;
        }

        String newCurrentPlayer = this.model.getCurrentPlayer();
        if (!Objects.equals(this.currentPlayer, newCurrentPlayer)){
            this.currentPlayer = newCurrentPlayer;
            this.view.getGame().updateGame(model.getBoard(), model.getCurrentPlayer());
        }
    }

    public void run(){
        this.view.init();
    }

    public static void main(String[] args) {
        GUI ui = new GUI();
        Client cl = new Client();
        Controller ct = new Controller(ui, cl);

        ct.run();
    }
}
