import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements ServerRemote {
   public static void main(String[] args) {
      try {
         ServerRemote object = new Server();
         ServerRemote stub = (ServerRemote)UnicastRemoteObject.exportObject(object, Env.serverPort);
         Registry registry = LocateRegistry.createRegistry(Env.serverPort);
         registry.rebind(Env.serverName, stub);
         System.out.println("Server successfully bounded on port: 1099");
      } catch (Exception ex) {
         System.err.println("Error while trying to bound server");
         System.err.println("Exception: " + ex.getMessage());
         System.err.println("Stack Trace: ");
         ex.printStackTrace();
      }
   }

   public String sayHello() throws RemoteException {
      return "Hello World";
   }
}
