/**
 * Serializable object representing a robot vacuum's status for JMS communication
 * 
 * @Larissa Okabayashi
 * @19/06
 */

import java.io.Serializable;

public class RobotStatus implements Serializable {
    private int cleaningProgress;  // 0.0 to 100.0 (%)
    private int batteryLevel;      // 0.0 to 100.0 (%)
    private Boolean isCleaning;
    private Boolean isDocked;

    public RobotStatus(int cleaningProgress, int batteryLevel, 
                      Boolean isCleaning, Boolean isDocked) {
        this.cleaningProgress = cleaningProgress;
        this.batteryLevel = batteryLevel;
        this.isCleaning = isCleaning;
        this.isDocked = isDocked;
    }

    // Getters and setters
    public int getCleaningProgress() {
        return cleaningProgress;
    }

    public void setCleaningProgress(int progress) {
        this.cleaningProgress = progress;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int level) {
        this.batteryLevel = level;
    }

    public Boolean isCleaning() {
        return isCleaning;
    }

    public Boolean isDocked() {
        return isDocked;
    }

    @Override
    public String toString() {
        return String.format(
            "RobotStatus[Progress: %s, Battery: %s, %s, %s]",
            cleaningProgress,  // Automatically converts float to String
            batteryLevel,      // Automatically converts float to String
            isCleaning ? "Cleaning" : "Idle",
            isDocked ? "Docked" : "Undocked"
        );
    }
}

