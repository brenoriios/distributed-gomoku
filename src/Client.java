import env.Env;
import view.Board;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Objects;

public class Client {
   static Player player;
   static Board board;
   static IGomoku stub;

   private Client(String host) {
//      try{
//         Registry registry = LocateRegistry.getRegistry(host);
//         stub = (IGomoku)registry.lookup(Env.serverName);
//         board = new Board();
//      } catch (Exception ex) {}
   }

   public static void main(String[] args) {
      String host = args.length < 1 ? null : args[0];

      try {
         Registry registry = LocateRegistry.getRegistry(host);
         stub = (IGomoku)registry.lookup(Env.serverName);

         player = stub.enterGame(new Date().toString());

         do {
            System.out.println("Aguardando início do jogo");
         } while (!stub.gameStarted());

         board = new Board();

         for(int x = 0; x < IGomoku.width; x++){
            for(int y = 0; y < IGomoku.height; y++) {
               int finalX = x;
               int finalY = y;
               board.buttons[x][y].addActionListener(e -> {
                  placePiece(finalX, finalY);
               });
            }
         }

         while (true){
            Thread.sleep(1000);
//            board.update(stub.getBoard());

            if (stub.getWinner() != null){
               System.out.println("Vencedor " + stub.getWinner());
               board.quit();
            }
         }

//         gameLoop(stub);
      } catch (Exception ex) {
         System.err.println("Client exception: " + ex);
         System.err.println("Stack trace: ");
         ex.printStackTrace();
      }
   }

   public static void placePiece(int x, int y){
      try {
         stub.placePiece(player, x, y);
         board.update(stub.getBoard());
      } catch (Exception e) {}
   }

   public static void gameLoop(IGomoku stub) {
      try {
          do {
              while (!Objects.equals(stub.getCurrentPlayer(), player.getId())) {
                  System.out.flush();
                  System.out.println("Não é sua vez");
              }

              printBoard(stub.getBoard());
              System.out.println("Faça sua jogada: ");
              String[] coords = System.console().readLine().split(",");
              int x = Integer.parseInt(coords[0]);
              int y = Integer.parseInt(coords[1]);

              stub.placePiece(player, x, y);
          } while (stub.getWinner() == null);
         stub.destroy();
      } catch (Exception ex) {
         // Erro
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
