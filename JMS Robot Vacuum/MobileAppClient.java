import serobjs.RobotStatus;
import javax.jms.JMSException;

import serobjs.RobotStatus;
import javax.jms.*;

public class MobileAppClient {
    private static final String COMMAND_QUEUE = "CommandQueue";
    private static final String STATUS_TOPIC = "RobotStatus.T";
    private static final String ALERT_TOPIC = "RobotAlerts.T";
    
    public static void main(String[] args) {
        try {
            // Initialize JMS components
            MobileAppCaller caller = new MobileAppCaller(COMMAND_QUEUE);
            MobileAppResponder responder = new MobileAppResponder(STATUS_TOPIC, ALERT_TOPIC);
            
            // Initialize application components
            CleaningSession session = new CleaningSession();
            PreferenceManager prefMgr = new PreferenceManager(new RoomPreferenceStorage());
            Logger logger = new Logger();
            NotificationManager notificationManager = new NotificationManager();
            
            // Create controller with all dependencies
            CleaningController controller = new CleaningController(
                session, prefMgr, logger, caller, responder, notificationManager);
            
            // Create status view
            RobotStatus initialStatus = caller.getCurrentStatus();
            CleaningStatusView statusView = new CleaningStatusView(controller, initialStatus);
            session.registerObserver(statusView);
            
            // Set up asynchronous notification handlers
            responder.addStatusListener(status -> {
                statusView.update(status);
                //notificationManager.notifyStatusChange(status);
                // Auto-refresh when status updates come in
                //statusView.refresh();
            });
            
            responder.addErrorListener(error -> {
                System.err.println("Error: " + error);
                try {
                    notificationManager.showErrorAlert(error);
                } catch (JMSException e) {
                    System.err.println("Error notification failed: " + e.getMessage());
                }
            });
            
            // Initialize text input system
            Parser parser = new Parser();
            TextInput textInput = new TextInput(caller, parser, controller);
            
            // Start the text input interface (blocks until quit command)
            System.out.println("Starting robot control system...");
            textInput.start();
            
            // Cleanup when text input completes
            controller.shutdown();
            caller.close();
            responder.close();
            
        } catch (Exception e) {
            System.err.println("Application error: ");
            e.printStackTrace();
        }
    }
}