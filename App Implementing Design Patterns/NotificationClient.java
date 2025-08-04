import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NotificationClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            NotificationService service = (NotificationService) registry.lookup("NotificationService");

            System.out.println("Calling notifyReturnToDock...");
            service.notifyReturnToDock();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

