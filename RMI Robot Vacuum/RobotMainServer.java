
/**
 * Write a description of class RobotMainServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;


public class RobotMainServer {
    public RobotMainServer() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry();
        RobotController robot = new RobotController();
        registry.rebind( "Robot", robot);
        System.out.println( "Robot registered" );
    }
    
    public static void startRegistry() throws RemoteException
    {
        LocateRegistry.createRegistry( Registry.REGISTRY_PORT);
        RemoteServer.setLog( System.err);
    }

    public static void main(String[] args) {
        try {
            new RobotMainServer();
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

