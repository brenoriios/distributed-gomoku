package engine;

import env.Env;
import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private SwingPropertyChangeSupport propChangeFirer;

    private Player player;
    private IGomoku stub;

    public Client() {}

    public boolean connect(String name){
          try{
             Registry registry = LocateRegistry.getRegistry(Env.hostAddress);
             this.stub = (IGomoku)registry.lookup(Env.serverName);
             this.player = this.stub.enterGame(name);

             return true;
          } catch (Exception _) {}

          return false;
    }

    public String[][] getBoard(){
        try{
            return this.stub.getBoard();
        } catch (Exception _) {}

        return null;
    }

   public void placePiece(int x, int y){
      try {
         this.stub.placePiece(this.player, x, y);
      } catch (Exception _) {}
   }

   public Player getPlayer(){
        return this.player;
   }

   public Player getOpponent(){
       try {
           return this.stub.getOpponent(this.player.getId());
       } catch (Exception _) {}

       return new Player();
   }

   public String getCurrentPlayer(){
       try {
            return this.stub.getCurrentPlayer();
       } catch (Exception _) {}

       return "";
   }

   public String getWinner(){
       try {
           return this.stub.getWinner();
       } catch (Exception _) {}

       return "";
   }

   public boolean isGameStarted(){
        try {
           return this.stub.isGameStarted();
        } catch (Exception _) {}

        return false;
   }
}
