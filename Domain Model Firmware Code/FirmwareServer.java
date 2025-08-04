import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Write a description of class FirmwareServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FirmwareServer {
    public static void main(String[] args) {
        try {
            FirmwareRemote firmware = new FirmwareImpl();
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.rebind("firmware", firmware);
            System.out.println("Firmware server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
