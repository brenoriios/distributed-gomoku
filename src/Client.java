import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
   private Client() { }

   public static void main(String[] args) {
      String host = args.length < 1 ? null : args[0];

      try {
         Registry registry = LocateRegistry.getRegistry(host);
         IGomoku stub = (IGomoku)registry.lookup(Env.serverName);

         stub.enterGame("P1");
         stub.enterGame("P2");

         stub.createBoard();
         stub.startGame();

         printBoard(stub.getBoard());
         Player p = stub.getCurrentPlayer();
         System.out.println(p.getName());

         stub.placePiece(5, 5);
         printBoard(stub.getBoard());
         System.out.println(stub.getCurrentPlayer().getName());
      } catch (Exception ex) {
         System.err.println("Client exception: " + ex);
         System.err.println("Stack trace: ");
         ex.printStackTrace();
      }
   }

   public static void printBoard(String[][] board){
      for (int x = 0; x < board.length - 1; x++){
         for (int y = 0; y < board[0].length - 1; y++){
            System.out.print(board[x][y]);
         }
         System.out.println();
      }
   }
}
