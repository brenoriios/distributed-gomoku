package engine;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGomoku extends Remote {
    int height = 15;
    int width = 15;

    Player enterGame(String name) throws RemoteException;

    void createBoard() throws RemoteException;
    void startGame() throws RemoteException;
    boolean isGameStarted() throws RemoteException;

    boolean placePiece(Player player, int x, int y) throws RemoteException;
    void switchPlayer() throws RemoteException;
    Player getOpponent(String playerId) throws RemoteException;
    String getCurrentPlayer() throws RemoteException;

    String[][] getBoard() throws RemoteException;

    boolean checkValidPosition(int x, int y) throws RemoteException;
    String getWinner() throws RemoteException;

    void destroy() throws RemoteException;
    boolean scoredOnHorizontal(Player player, int x, int y) throws RemoteException;
    boolean scoredOnVertical(Player player, int x, int y) throws RemoteException;
    boolean scoredOnDiagonalFromLeft(Player player, int x, int y) throws RemoteException;
    boolean scoredOnDiagonalFromRight(Player player, int x, int y) throws RemoteException;
}
