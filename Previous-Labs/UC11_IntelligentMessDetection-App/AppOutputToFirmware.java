import java.time.LocalDateTime;

/**
 * Data sent from the app to the robot firmware.
 * Represents the user's decision or override action.
 */
public class AppOutputToFirmware {
    public String customAction;           // Optional manual override command
    public LocalDateTime timestamp;       // When the decision was made

    public AppOutputToFirmware(String customAction, LocalDateTime timestamp) {
        this.customAction = customAction;
        this.timestamp = timestamp;
    }

    public void display() {
        System.out.println("Custom Action: " + customAction);
        System.out.println("Timestamp: " + timestamp);
    }
}