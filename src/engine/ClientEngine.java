package engine;

import env.Env;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientEngine {
    private Player player;
    private IGomoku stub;

    public ClientEngine() {
    }

    public boolean connect(String name) {
        try {
            Registry registry = LocateRegistry.getRegistry(Env.hostAddress);
            this.stub = (IGomoku) registry.lookup(Env.serverName);
            this.player = this.stub.enterGame(name);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String[][] getBoard() {
        try {
            return this.stub.getBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void placePiece(int row, int col) {
        try {
            this.stub.placePiece(this.player, row, col);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public Player getOpponent() {
        try {
            return this.stub.getOpponent(this.player.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Player();
    }

    public String getCurrentPlayer() {
        try {
            return this.stub.getCurrentPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getWinner() {
        try {
            return this.stub.getWinner();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public boolean isGameStarted() {
        try {
            return this.stub.isGameStarted();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
