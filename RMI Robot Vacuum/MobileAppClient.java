
/**
 * Write a description of class MobileAppClient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class MobileAppClient
{
    private RobotStatusInterface robotStatus;
    private CleaningController cleaningController;
    private boolean monitoring = true;
    
    public static void main(String[] args) {
        try {
            // Setup RMI connection
            Registry registry = LocateRegistry.getRegistry("localhost");
            RobotStatusInterface robotStatus = (RobotStatusInterface) registry.lookup("Robot");
            
            // Create components
            CleaningSession session = new CleaningSession();
            RoomPreferenceStorage prefs = new RoomPreferenceStorage();
            PreferenceManager prefMgr = new PreferenceManager(prefs);
            Logger logger = new Logger();
            
            // Create controller with robot integration
            CleaningController controller = new CleaningController(session, prefMgr, logger);
            controller.setRemoteInterface(robotStatus);
            session.setRobotStatusInterface(robotStatus);
            
            // Setup views
            CleaningStatusView statusView = new CleaningStatusView(controller);
            session.registerObserver(statusView);
            
            // Setup notification manager
            NotificationManager notifier = new NotificationManager();
            controller.addNotifier(notifier); // Now this will work
            
            // Start text interface
            new TextInput(robotStatus, new Parser(), controller).start();
            
            // Initial status refresh
            statusView.refresh();
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    
    private void monitorStatus() {
        try {
            while (monitoring) {
                RobotStatus status = robotStatus.getStatus();
                System.out.println("Current Status:");
                System.out.println("- Progress: " + status.getCleaningProgress() + "%");
                System.out.println("- Battery: " + status.getBatteryLevel() + "%");
                System.out.println("- Status: " + 
                    (status.isCleaning() ? "Cleaning" : "Idle"));
                
                // Check for low battery
                if (status.getBatteryLevel() < 15 && status.isCleaning()) {
                    System.out.println("Warning: Low battery! Robot should return to dock.");
                }
                
                Thread.sleep(2000); // Update every 2 seconds
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stopMonitoring() {
        monitoring = false;
    }
}