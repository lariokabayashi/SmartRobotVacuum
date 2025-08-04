/**
 * Data sent from the robot firmware to the app.
 * Encapsulates the result of intelligent mess detection.
 */
public class FirmwareOutput {
    public String messType;             // e.g., "pet waste"
    public int priority;                // e.g., 1 (high), 2 (medium), 3 (low)
    public float confidence;            // AI confidence score (0.0 to 1.0)
    public String location;             // Location of mess
    public boolean requiresUser;        // True if user input is required
    private Enum recommendedAction;      //suggest robot response (CLEAN, AVOID, ALERT_USER)
    private AppController app;
    
    public FirmwareOutput(String messType, int priority, float confidence, String location,boolean requiresUser) {
        this.messType = messType;
        this.priority = priority;
        this.confidence = confidence;
        this.location = location;
        this.requiresUser = requiresUser;
        this.recommendedAction = recommendedAction;
    }

    public void display() {
        System.out.println("Mess Type: " + messType);
        System.out.println("Priority: " + priority);
        System.out.println("Confidence: " + confidence);
        System.out.println("Location: " + location);
        System.out.println("Requires User: " + requiresUser);
        System.out.println("Recommended Action: " + recommendedAction);

    }
    
    public boolean isUserInputRequired() {
        return requiresUser;
    }
    
    public String getFormattedLocation() {
    // Convert raw coordinates to room names
        return "Zone: " + location;
    }
    
    public boolean isHighPriority() {
    // Determine if the mess is high priority (e.g., 7+)
        return priority >= 7;
    }
    
    public boolean isValid() {
        // Check if the object contains valid information
        return messType != null && !messType.isEmpty() &&
               location != null && !location.isEmpty() &&
               confidence >= 0.0 && confidence <= 1.0;
    }
}