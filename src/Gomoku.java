import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Random;

public class Gomoku implements IGomoku{
    private final String[][] board = new String[width][height];
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    public Gomoku() throws RemoteException {
        this.createBoard();
    }

    @Override
    public void enterGame(String name) throws RemoteException {
        Player newPlayer = new Player(name);

        if(playerOne == null){
            this.playerOne = newPlayer;
            this.playerOne.setPieceColor(Env.pieceBlack);
            return;
        }

        this.playerTwo = newPlayer;
        this.playerTwo.setPieceColor(Env.pieceWhite);
    }

    @Override
    public void createBoard() throws RemoteException {
        for (int x = 0; x < this.board.length - 1; x++){
            for (int y = 0; y < this.board[0].length - 1; y++){
                this.board[x][y] = " " + Env.emptyCell + " ";
            }
        }
    }

    @Override
    public boolean startGame() throws RemoteException {
        if (playerOne == null || playerTwo == null){
            return true;
        }

        Random rand = new Random();
        int coinFlip = rand.nextInt(2);

        if (coinFlip == 0){
            this.currentPlayer = playerOne;
            return true;
        }

        this.currentPlayer = playerTwo;
        return true;
    }

    @Override
    public boolean placePiece(int x, int y) throws RemoteException {
        if (!checkValidPosition(x, y)) return false;

        this.board[x][y] = " " + this.currentPlayer.getPieceColor() + " ";
        this.switchPlayer();

        return true;
    }

    @Override
    public void switchPlayer() throws RemoteException {
        if(currentPlayer == playerOne){
            currentPlayer = playerTwo;
            return;
        }

        currentPlayer = playerOne;
    }

    @Override
    public Player getCurrentPlayer() throws RemoteException {
        return this.currentPlayer;
    }

    @Override
    public String[][] getBoard() throws RemoteException {
        return this.board;
    }

    @Override
    public boolean checkValidPosition(int x, int y) throws RemoteException {
        return Objects.equals(this.board[x][y], " E ");
    }
}
