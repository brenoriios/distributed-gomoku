package engine;

import env.Env;
import view.board.Board;
import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class Client {
    private SwingPropertyChangeSupport propChangeFirer;

   private Player player;
   private IGomoku stub;

   public Client() {
       propChangeFirer = new SwingPropertyChangeSupport(this);
   }

    public void setStub(IGomoku stub) {
        IGomoku oldStub = this.stub;
        this.stub = stub;
        propChangeFirer.firePropertyChange("stub", oldStub, stub);
    }

    public void addListener(PropertyChangeListener property) {
        propChangeFirer.addPropertyChangeListener(property);
    }

    public void connect(String name){
          try{
             Registry registry = LocateRegistry.getRegistry(Env.hostAddress);
             stub = (IGomoku)registry.lookup(Env.serverName);
             player = stub.enterGame(name);
          } catch (Exception ex) {}
    }

    public String[][] getBoard(){
        try{
            return this.stub.getBoard();
        } catch (Exception ex) {}

        return null;
    }

   public void placePiece(int x, int y){
      try {
         this.stub.placePiece(this.player, x, y);
      } catch (Exception e) {}
   }
}
