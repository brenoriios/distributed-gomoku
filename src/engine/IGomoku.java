package engine;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGomoku extends Remote {
    int maxRows = 15;
    int maxCols = 15;

    Player enterGame(String name) throws RemoteException;

    void createBoard() throws RemoteException;

    void startGame() throws RemoteException;

    boolean isGameStarted() throws RemoteException;

    void placePiece(Player player, int row, int col) throws RemoteException;

    void switchPlayer() throws RemoteException;

    Player getOpponent(String playerId) throws RemoteException;

    String getCurrentPlayer() throws RemoteException;

    String[][] getBoard() throws RemoteException;

    boolean checkValidPosition(int row, int col) throws RemoteException;

    String getWinner() throws RemoteException;

    boolean scoredOnHorizontal(Player player, int row, int col) throws RemoteException;

    boolean scoredOnVertical(Player player, int row, int col) throws RemoteException;

    boolean scoredFromTopLeftToBottomRight(Player player, int row, int col) throws RemoteException;

    boolean scoredFromTopRightToBottomLeft(Player player, int row, int col) throws RemoteException;
}
