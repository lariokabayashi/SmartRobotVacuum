/**
 * VIEW/Helper – central place for toast/pop-up style alerts.
 * Also observes CleaningSession progress for quick status ticks.
 */
import javax.swing.JOptionPane;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class NotificationManager extends UnicastRemoteObject implements Observer, NotificationInterface {
    private RobotStatusInterface robotStatus;
     public NotificationManager() throws RemoteException {
        super(); // This handles the export automatically
        try {
            Registry registry = LocateRegistry.getRegistry();
            robotStatus = (RobotStatusInterface) registry.lookup("Robot");
            
            // Just register the callback - no need to export again
            robotStatus.registerCallback(this);
            
            System.out.println("NotificationManager registered with robot");
        } catch (Exception e) {
            System.err.println("NotificationManager exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            new NotificationManager();
            System.out.println("NotificationManager running. Press Ctrl+C to exit.");
            
            // Keep the program running to receive callbacks
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Object arg) {
        if (arg instanceof Float f) {
            int percent = Math.round(f * 100);
            System.out.println("Cleaning progress: " + percent + "%");
            if (percent == 100) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Cleaning complete!");
                });
            }
        }
    }
    
    @Override
    public void notifyStatusChange(RobotStatus status) throws RemoteException {
        System.out.println("Status Update: " + status);
        javax.swing.SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, 
                "Status Update:\nProgress: " + status.getCleaningProgress() + "%\n" +
                "Battery: " + status.getBatteryLevel() + "%",
                "Robot Status",
                JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
    @Override
    public void notifyLowBattery() throws RemoteException {
        System.out.println("ALERT: Robot battery critically low!");
        javax.swing.SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, 
                "Battery critically low!\nPlease return robot to dock.", 
                "Low Battery", 
                JOptionPane.WARNING_MESSAGE);
        });
    }
    
    @Override
    public void notifyCleaningComplete() throws RemoteException {
        System.out.println("Cleaning completed successfully!");
        javax.swing.SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Cleaning complete!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
    public void update(float p) { 
        display(); 
    }
    
    public void display() { 
        System.out.println("Notification area refreshed"); 
    }
    
    public void showAlert(AlertType t) { 
        System.out.println("ALERT: " + t); 
    }
    
    public void returnToDock() {
        System.out.println("Triggering return to dock UI...");
        javax.swing.SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, 
                "Return the robot to dock.", 
                "Action Required", 
                JOptionPane.INFORMATION_MESSAGE);
        });
    }
}