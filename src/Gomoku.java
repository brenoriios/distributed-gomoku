import env.Env;

import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Gomoku implements IGomoku{
    private final String[][] board = new String[width][height];
    private boolean gameStarted = false;
    private Player playerOne;
    private Player playerTwo;
    private String currentPlayer;
    private String winner;

    public Gomoku() throws RemoteException {
        this.createBoard();
    }

    @Override
    public Player enterGame(String name) throws RemoteException {
        if (this.gameStarted) return null;

        String id = UUID.randomUUID().toString();
        Player newPlayer = new Player(id, name);

        if(playerOne == null){
            newPlayer.setPieceColor(Env.pieceBlack);
            this.playerOne = newPlayer;
        } else {
            newPlayer.setPieceColor(Env.pieceWhite);
            this.playerTwo = newPlayer;
        }

        this.startGame();

        return newPlayer;
    }

    @Override
    public void createBoard() throws RemoteException {
        for (int x = 0; x < this.board.length - 1; x++){
            for (int y = 0; y < this.board[0].length - 1; y++){
                this.board[x][y] = Env.emptyCell;
            }
        }
    }

    @Override
    public void startGame() throws RemoteException {
        if (playerOne == null || playerTwo == null){
            return;
        }

        this.gameStarted = true;

        Random rand = new Random();
        int coinFlip = rand.nextInt(2);

        if (coinFlip == 0){
            this.currentPlayer = playerOne.getId();
            return;
        }

        this.currentPlayer = playerTwo.getId();
    }

    @Override
    public boolean gameStarted() throws RemoteException {
        return this.gameStarted;
    }

    @Override
    public boolean placePiece(Player player, int x, int y) throws RemoteException {
        if (!Objects.equals(player.getId(), this.currentPlayer)) return false;
        if (!checkValidPosition(x, y)) return false;

        System.out.println("Jogador " + player.getId() + " fez sua jogada");
        this.board[x][y] = player.getPieceColor();

        if (
            this.scoredOnHorizontal(player, x, y) ||
            this.scoredOnVertical(player, x, y) ||
            this.scoredOnDiagonalFromLeft(player, x, y) ||
            this.scoredOnDiagonalFromRight(player, x, y)
        ) {
            this.winner = player.getId();
            return true;
        }

        this.switchPlayer();
        System.out.println("Vez do " + currentPlayer);
        return true;
    }

    @Override
    public void switchPlayer() throws RemoteException {
        if(Objects.equals(currentPlayer, playerOne.getId())){
            currentPlayer = playerTwo.getId();
            return;
        }

        currentPlayer = playerOne.getId();
    }

    @Override
    public String getCurrentPlayer() throws RemoteException {
        return this.currentPlayer;
    }

    @Override
    public String[][] getBoard() throws RemoteException {
        return this.board;
    }

    @Override
    public boolean checkValidPosition(int x, int y) throws RemoteException {
        return Objects.equals(this.board[x][y], Env.emptyCell);
    }

    @Override
    public String getWinner() throws RemoteException {
        return this.winner;
    }

    @Override
    public void destroy() throws RemoteException {
        this.gameStarted = false;
        this.playerOne = null;
        this.playerTwo = null;
        this.winner = null;
    }

    @Override
    public boolean scoredOnHorizontal(Player player, int x, int y) throws RemoteException {
        int dx = x;
        // b b b b b
        while (dx >= 0 && Objects.equals(this.board[dx][y], player.getPieceColor())){
            dx -= 1;
        }

        int pieceCount = 0;
        for(int i = 1; i <= 5; i++){
            if (!Objects.equals(this.board[dx + i][y], player.getPieceColor())) break;
            pieceCount++;
        }

        return pieceCount >= 5;
    }

    @Override
    public boolean scoredOnVertical(Player player, int x, int y) throws RemoteException {
        int dy = y;
        while (dy >= 0 && Objects.equals(this.board[x][dy], player.getPieceColor())){
            dy -= 1;
        }

        int pieceCount = 0;
        for(int i = 1; i <= 5; i++){
            if (!Objects.equals(this.board[x][dy + i], player.getPieceColor())) break;
            pieceCount++;
        }

        return pieceCount >= 5;
    }

    @Override
    public boolean scoredOnDiagonalFromLeft(Player player, int x, int y) throws RemoteException {
        int dx = x;
        int dy = y;
        while ((dx >= 0 && dy >= 0) && Objects.equals(this.board[dx][dy], player.getPieceColor())){
            dx -= 1;
            dy -= 1;
        }

        int pieceCount = 0;
        for(int i = 1; i <= 5; i++){
            if (!Objects.equals(this.board[dx + i][dy + i], player.getPieceColor())) break;
            pieceCount++;
        }

        return pieceCount >= 5;
    }

    @Override
    public boolean scoredOnDiagonalFromRight(Player player, int x, int y) throws RemoteException {
        int dx = x;
        int dy = y;
        while ((dx >= 0 && dy >= 0) && Objects.equals(this.board[dx][dy], player.getPieceColor())){
            dx += 1;
            dy += 1;
        }

        int pieceCount = 0;
        for(int i = 1; i <= 5; i++){
            if (!Objects.equals(this.board[dx - i][dy - i], player.getPieceColor())) break;
            pieceCount++;
        }

        return pieceCount >= 5;
    }
}
