/**
 * Defines all message types used in communication between 
 * the Robot Firmware (server) and Mobile App (client) via JMS.
 * 
 * Each enum value represents a specific message type with:
 * - A consistent naming convention
 * - Clear purpose documentation
 */
public enum RobotMessageKeys {
    // Client -> Server commands
    GET_STATUS("GetStatus"),
    START_CLEANING("StartCleaning"),
    STOP_CLEANING("StopCleaning"),
    RETURN_TO_DOCK("ReturnToDock"),
    
    // Server -> Client notifications
    STATUS_UPDATE("StatusUpdate"),
    ERROR_NOTIFICATION("ErrorNotification"),
    BATTERY_LEVEL_UPDATE("BatteryLevelUpdate"),
    CLEANING_PROGRESS_UPDATE("CleaningProgressUpdate"),
    OPERATION_COMPLETE("OperationComplete"),
    EOT("EOT");
    
    private final String messageName;
    
    RobotMessageKeys(String messageName) {
        this.messageName = messageName;
    }
    
    @Override
    public String toString() {
        return messageName;
    }
}
