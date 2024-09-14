import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Client {
   static Player player;

   private Client() { }

   public static void main(String[] args) {
      String host = args.length < 1 ? null : args[0];

      try {
         Registry registry = LocateRegistry.getRegistry(host);
         IGomoku stub = (IGomoku)registry.lookup(Env.serverName);

         player = stub.enterGame(new Date().toString());

         while (!stub.gameStarted()){
            System.out.println("Aguardando início do jogo");
         }

         gameLoop(stub);
      } catch (Exception ex) {
         System.err.println("Client exception: " + ex);
         System.err.println("Stack trace: ");
         ex.printStackTrace();
      }
   }

   public static void gameLoop(IGomoku stub) {
      try {
         while (true) {
            while(!Objects.equals(stub.getCurrentPlayer(), player.getId())){
               System.out.flush();
               System.out.println("Não é sua vez");
            }

            printBoard(stub.getBoard());
            System.out.println("Faça sua jogada: ");
            String[] coords = System.console().readLine().split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);

            stub.placePiece(player, x, y);

            if(stub.getWinner() != null){
               break;
            }
         }
         stub.destroy();
      } catch (Exception ex) {
         return;
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
