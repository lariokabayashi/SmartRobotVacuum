import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Write a description of class NotificationServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NotificationServer {
    public static void main(String[] args) throws Exception {
        NotificationManager manager = new NotificationManager();
        NotificationService service = new NotificationServiceImpl(manager);

        Registry registry = LocateRegistry.createRegistry(1099); // Default port
        registry.rebind("NotificationService", service);

        System.out.println("NotificationService is up and running...");
    }
}