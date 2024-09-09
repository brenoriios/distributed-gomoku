import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGomoku extends Remote {
    int height = 15;
    int width = 15;

    void enterGame(String name) throws RemoteException;

    void createBoard() throws RemoteException;
    boolean startGame() throws RemoteException;

    boolean placePiece(int x, int y) throws RemoteException;
    void switchPlayer() throws RemoteException;
    Player getCurrentPlayer() throws RemoteException;

    String[][] getBoard() throws RemoteException;

    boolean checkValidPosition(int x, int y) throws RemoteException;
}
