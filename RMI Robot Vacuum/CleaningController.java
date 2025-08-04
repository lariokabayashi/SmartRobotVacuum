/**
 * CONTROLLER – orchestrates cleaning workflow.
 * Delegates to {@link CleaningSession}, {@link PreferenceManager}, {@link Logger}.
 */
import java.util.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class CleaningController {
    private final CleaningSession           session;
    private final PreferenceManager         prefMgr;
    private final Logger                    logger;
    private final List<NotificationManager> notifiers = new ArrayList<>();
    private RobotStatusInterface robotStatus;
    
    public CleaningController(CleaningSession s, PreferenceManager pm, Logger lg) {
        this.session = s;
        this.prefMgr = pm;
        this.logger  = lg;
    }
    
    public void startCleaning() {
        try {
            if (robotStatus != null && robotStatus.startCleaning()) {
                session.executeAction();
                logger.log("Cleaning started successfully");
            } else {
                logger.log("Failed to start cleaning");
            }
        } catch (RemoteException e) {
            logger.log("Communication error: " + e.getMessage());
        }
    }

    public void stopCleaning() {
        try {
            if (robotStatus != null && robotStatus.stopCleaning()) {
                logger.log("Cleaning stopped successfully");
            } else {
                logger.log("Failed to stop cleaning");
            }
        } catch (RemoteException e) {
            logger.log("Communication error: " + e.getMessage());
        }
    }
    
    /**
     * Adds a notification manager to receive status updates
     * @param notifier The NotificationManager to add
     */
    public void addNotifier(NotificationManager notifier) {
        if (notifier != null && !notifiers.contains(notifier)) {
            notifiers.add(notifier);
        }
    }

    /**
     * Removes a notification manager
     * @param notifier The NotificationManager to remove
     */
    public void removeNotifier(NotificationManager notifier) {
        notifiers.remove(notifier);
    }

    // Update the existing notifyCompletion method to use notifiers
    public void notifyCompletion() {
        for (NotificationManager n : notifiers) {
            n.showAlert(AlertType.INFO);
        }
    }
    
    // Add this method to push status updates to all notifiers
    public void pushStatusUpdate(RobotStatus status) {
        for (NotificationManager n : notifiers) {
            try {
                n.notifyStatusChange(status);
            } catch (RemoteException e) {
                System.err.println("Failed to notify " + n + ": " + e.getMessage());
                removeNotifier(n); // Remove faulty notifiers
            }
        }
    }

    public void updateRobotStatusDisplay() {
        try {
            if (robotStatus != null) {
                RobotStatus status = robotStatus.getStatus();
                // Update all notifiers with the new status
                pushStatusUpdate(status);
            }
        } catch (RemoteException e) {
            logger.log("Status update error: " + e.getMessage());
        }
    }

    public void selectAction(CleaningAction a) { session.setCurrentAction(a); }

    public void forwardPreference(String room, CleaningPreference p) {
        prefMgr.savePreference(room, p);
    }
  
    public void sendLog(String msg) { logger.log(msg); }
    
    // Add setter for remote interface
    public void setRemoteInterface(RobotStatusInterface robotStatus) {
        this.robotStatus = robotStatus;
    }
    public void startStatusMonitor() {
    new Thread(() -> {
        while (true) {
            try {
                updateRobotStatusDisplay();
                Thread.sleep(2000); // Update every 2 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }).start();
}
}

