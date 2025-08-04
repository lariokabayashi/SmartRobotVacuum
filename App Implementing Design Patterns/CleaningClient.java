import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CleaningClient {
    public static void main(String[] args) {
        try {
            // Connect to registry and service
            Registry registry = LocateRegistry.getRegistry("localhost"); // or server IP
            CleaningService service = (CleaningService) registry.lookup("CleaningService");

            // Setup session + observer
            CleaningSession session = new CleaningSession();
            NotificationManager manager = new NotificationManager();
            session.registerObserver(manager);

            // Poll loop
            while (true) {
                float progress = service.getProgress();
                session.setProgress(progress); // calls notifyObservers(progress)

                if (progress >= 1.0f) break;
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

