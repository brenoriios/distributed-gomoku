import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRemote extends Remote {
   String sayHello() throws RemoteException;
}
