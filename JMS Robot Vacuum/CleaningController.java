import javax.jms.*;
import serobjs.RobotStatus;
import java.util.*;

    
public class CleaningController {
    private final CleaningSession session;
    private final PreferenceManager prefMgr;
    private final Logger logger;
    private final MobileAppCaller appCaller;
    private final NotificationHandler handler;
    private final NotificationInterface notifier;
    private Thread statusMonitorThread;
    private final List<NotificationInterface> notifiers = new ArrayList<>();
    
    public CleaningController(CleaningSession session, PreferenceManager prefMgr, 
                            Logger logger, MobileAppCaller appCaller, 
                            NotificationHandler handler, NotificationInterface notifier) throws JMSException {
        this.session = session;
        this.prefMgr = prefMgr;
        this.logger = logger;
        this.appCaller = appCaller;
        this.handler = handler;
        this.notifier = notifier;
        
        setupNotificationHandlers();
    }

    private void setupNotificationHandlers() {
        handler.addStatusListener(status -> {
            logger.log("Status update: " + status);
            session.setProgress(status.getCleaningProgress());
            
            if (status.getBatteryLevel() < 20) {
                try {
                    notifier.notifyLowBattery(status.getBatteryLevel());
                } catch (JMSException e) {
                    logger.log("Failed to send battery alert: " + e.getMessage());
                }
            }
        });

        handler.addErrorListener(error -> {
            logger.log("Error notification: " + error);
        });
    }

    public void addNotifier(NotificationInterface notifier) {
        if (notifier != null) {
            notifiers.add(notifier);
        }
    }

    public void removeNotifier(NotificationInterface notifier) {
        notifiers.remove(notifier);
    }
    /*
    public void updateRobotStatusDisplay(RobotStatus status) {
        notifiers.forEach(n -> {
            try {
                n.notifyStatusChange(status);
            } catch (JMSException e) {
                logger.log("Failed to update status: " + e.getMessage());
            }
        });
    }*/

    public void shutdown() {
        stopStatusMonitor();
        handler.removeStatusListener(this::handleStatusUpdate);
        handler.removeErrorListener(this::handleError);
        notifiers.clear();
    }

    private void handleStatusUpdate(RobotStatus status) {
        logger.log(status.toString());
        session.setProgress(status.getCleaningProgress());
        if (status.getBatteryLevel() < 20) {
            try {
                notifier.notifyLowBattery(status.getBatteryLevel());
            } catch (JMSException e) {
                logger.log("Failed to notify low battery: " + e.getMessage());
            }
        }
    }

    private void handleError(String errorMessage) {
        logger.log("Error: " + errorMessage);
    }

    public void startCleaning(String mode) throws JMSException {
        RobotStatus currentStatus = appCaller.getCurrentStatus();
        
        if (currentStatus.isCleaning()) {
            throw new JMSException("Robot is already cleaning");
        }
        
        if (currentStatus.getBatteryLevel() < 10) {
            throw new JMSException("Battery too low to start cleaning");
        }
        
        if (appCaller.startCleaning()) {
            session.executeAction();
            logger.log("Cleaning started in " + mode + " mode");
            startStatusMonitor();
        } else {
            throw new JMSException("Start cleaning command rejected. Current status: " + currentStatus);
        }
    }

    private void startStatusMonitor() {
        statusMonitorThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    RobotStatus status = appCaller.getCurrentStatus();
                    notifier.notifyStatusChange(status);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                // Thread was interrupted, exit normally
            } catch (JMSException e) {
                logger.log("Status monitor error: " + e.getMessage());
            }
        });
        statusMonitorThread.start();
    }

    private void stopStatusMonitor() {
        if (statusMonitorThread != null && statusMonitorThread.isAlive()) {
            statusMonitorThread.interrupt();
        }
    }
    
    public boolean returnToDock() throws JMSException {
        if (appCaller.returnToDock()) {
            logger.log("Returning to dock");
            return true;
        } else {
            throw new JMSException("Return to dock failed");
        }
    }
    
    public void savePreference(String room, CleaningPreference pref) {
        prefMgr.savePreference(room, pref);
        logger.log("Preference saved for " + room);
    }
}