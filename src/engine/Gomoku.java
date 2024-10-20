package engine;

import env.Common;

import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Gomoku implements IGomoku {
    private final String[][] board = new String[maxRows][maxCols];
    private boolean gameStarted = false;
    private Player playerOne;
    private Player playerTwo;
    private String currentPlayer;
    private String winner = "";

    public Gomoku() throws RemoteException {
        this.createBoard();
    }

    @Override
    public Player enterGame(String name) throws RemoteException {
        if (this.gameStarted) return null;

        String id = UUID.randomUUID().toString();
        Player newPlayer = new Player(id, name);

        if (playerOne == null) {
            newPlayer.setPieceColor(Common.pieceBlack);
            this.playerOne = newPlayer;
        } else {
            newPlayer.setPieceColor(Common.pieceWhite);
            this.playerTwo = newPlayer;
        }

        this.startGame();

        return newPlayer;
    }

    @Override
    public void createBoard() throws RemoteException {
        for (int row = 0; row < maxCols; row++) {
            for (int col = 0; col < maxRows; col++) {
                this.board[row][col] = Common.emptyCell;
            }
        }
    }

    @Override
    public void startGame() throws RemoteException {
        if (playerOne == null || playerTwo == null) {
            return;
        }

        this.gameStarted = true;

        Random rand = new Random();
        int coinFlip = rand.nextInt(2);

        if (coinFlip == 0) {
            this.currentPlayer = playerOne.getId();
            return;
        }

        this.currentPlayer = playerTwo.getId();
    }

    @Override
    public boolean isGameStarted() throws RemoteException {
        return this.gameStarted;
    }

    @Override
    public void placePiece(Player player, int row, int col) throws RemoteException {
        if (!Objects.equals(player.getId(), this.currentPlayer)) return;
        if (!checkValidPosition(row, col)) return;

        System.out.println(player.getId() + " fez sua jogada");
        this.board[row][col] = player.getPieceColor();

        if (
            this.scoredOnHorizontal(player, row, col) ||
            this.scoredOnVertical(player, row, col) ||
            this.scoredFromTopLeftToBottomRight(player, row, col) ||
            this.scoredFromTopRightToBottomLeft(player, row, col)
        ) {
            this.winner = player.getName();
        }

        this.switchPlayer();
        System.out.println("Vez do " + currentPlayer);
    }

    @Override
    public void switchPlayer() throws RemoteException {
        if (Objects.equals(currentPlayer, playerOne.getId())) {
            currentPlayer = playerTwo.getId();
            return;
        }

        currentPlayer = playerOne.getId();
    }

    @Override
    public Player getOpponent(String playerId) throws RemoteException {
        if (Objects.equals(this.playerOne.getId(), playerId)) return this.playerTwo;

        return this.playerOne;
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
    public boolean checkValidPosition(int row, int col) throws RemoteException {
        if (row >= maxRows || col >= maxCols) return false;

        return Objects.equals(this.board[row][col], Common.emptyCell);
    }

    @Override
    public String getWinner() throws RemoteException {
        return this.winner;
    }

    @Override
    public boolean scoredOnHorizontal(Player player, int row, int col) throws RemoteException {
        int count = 0;
        int auxCol = col;

        while (auxCol >= 0 && Objects.equals(this.board[row][auxCol], player.getPieceColor())) {
            auxCol--;
            count++;
        }

        auxCol = ++col;
        while (auxCol < maxCols && Objects.equals(this.board[row][auxCol], player.getPieceColor())) {
            auxCol++;
            count++;
        }

        return count >= 5;
    }

    @Override
    public boolean scoredOnVertical(Player player, int row, int col) throws RemoteException {
        int count = 0;
        int auxRow = row;

        while (auxRow >= 0 && Objects.equals(this.board[auxRow][col], player.getPieceColor())) {
            auxRow--;
            count++;
        }

        auxRow = ++row;
        while (auxRow < maxRows && Objects.equals(this.board[auxRow][col], player.getPieceColor())) {
            auxRow++;
            count++;
        }

        return count >= 5;
    }

    @Override
    public boolean scoredFromTopLeftToBottomRight(Player player, int row, int col) throws RemoteException {
        int count = 0;
        int auxRow = row;
        int auxCol = col;

        while (auxRow >= 0 && auxCol >= 0 && Objects.equals(this.board[auxRow][auxCol], player.getPieceColor())) {
            count++;
            auxRow--;
            auxCol--;
        }

        auxRow = ++row;
        auxCol = ++col;
        while (auxRow < maxRows && auxCol < maxCols && Objects.equals(this.board[auxRow][auxCol], player.getPieceColor())) {
            count++;
            auxRow++;
            auxCol++;
        }

        return count >= 5;
    }

    @Override
    public boolean scoredFromTopRightToBottomLeft(Player player, int row, int col) throws RemoteException {
        int count = 0;
        int auxRow = row;
        int auxCol = col;

        while (auxRow >= 0 && auxCol < maxCols && Objects.equals(this.board[auxRow][auxCol], player.getPieceColor())) {
            count++;
            auxRow--;
            auxCol++;
        }

        auxRow = ++row;
        auxCol = --col;
        while (auxRow < maxRows && auxCol >= 0 && Objects.equals(this.board[auxRow][auxCol], player.getPieceColor())) {
            count++;
            auxRow++;
            auxCol--;
        }

        return count >= 5;
    }
}
