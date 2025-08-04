import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Write a description of class CleaningServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CleaningServer {
    public static void main(String[] args) throws Exception {
        CleaningServiceImpl service = new CleaningServiceImpl();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("CleaningService", service);
        System.out.println("CleaningService ready.");
    }
}
