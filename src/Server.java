import engine.Gomoku;
import engine.IGomoku;
import env.Env;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {
        try {
            Gomoku object = new Gomoku();
            IGomoku stub = (IGomoku) UnicastRemoteObject.exportObject(object, Env.serverPort);

            Registry registry = LocateRegistry.createRegistry(Env.serverPort);
            registry.rebind(Env.serverName, stub);

            System.out.println("Server successfully bounded on port: " + Env.serverPort);
        } catch (Exception ex) {
            System.err.println("Error while trying to bound server");
            System.err.println("Exception: " + ex.getMessage());
            System.err.println("Stack Trace: ");
            ex.printStackTrace();
        }
    }
}
