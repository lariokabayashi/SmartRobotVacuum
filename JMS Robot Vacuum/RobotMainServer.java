import javax.jms.JMSException;
import serobjs.RobotStatus;
import javax.jms.*;
import serobjs.RobotStatus;

public class RobotMainServer {
    private static final String COMMAND_QUEUE = "CommandQueue";
    private static final String STATUS_TOPIC = "RobotStatus.T";
    private static final String ALERT_TOPIC = "RobotAlerts.T";
    
    public static void main(String[] args) {
        try {
            final RobotSystem robot = new RobotSystem();
            final RobotResponder responder = new RobotResponder(robot, COMMAND_QUEUE);
            final RobotCaller caller = new RobotCaller(STATUS_TOPIC, ALERT_TOPIC);
            
            responder.connect();
            caller.connect();
            responder.listen();
            
            System.out.println("Robot server started. Listening on queue: " + COMMAND_QUEUE);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down...");
                try {
                    responder.eot();
                    responder.close();
                    caller.close();
                } catch (JMSException e) {
                    System.err.println("Shutdown error: " + e.getMessage());
                }
            }));

            // Status monitoring thread
            Thread statusThread = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        RobotStatus status = robot.getStatus();
                        caller.notifyStatusChange(status);
                        
                        if (status.getBatteryLevel() < 20) {
                            caller.notifyLowBattery(status.getBatteryLevel());
                        }
                        
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (JMSException e) {
                    System.err.println("Status update error: " + e.getMessage());
                }
            });
            statusThread.setDaemon(true);
            statusThread.start();

            // Keep main thread alive
            Thread.currentThread().join();
            
        } catch (JMSException | InterruptedException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}