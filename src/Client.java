import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
   private Client() { }

   public static void main(String[] args) {
      String host = args.length < 1 ? null : args[0];

      try {
         Registry registry = LocateRegistry.getRegistry(host);
         ServerRemote stub = (ServerRemote)registry.lookup(Env.serverName);
         String response = stub.sayHello();
         System.out.println(response);
      } catch (Exception ex) {
         System.err.println("Client exception: " + ex);
         System.err.println("Stack trace: ");
         ex.printStackTrace();
      }

   }
}
