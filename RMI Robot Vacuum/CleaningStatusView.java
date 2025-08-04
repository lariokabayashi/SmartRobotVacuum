/**
 * VIEW – live progress bar / label.
 * Observes {@link CleaningSession}.
 */
public class CleaningStatusView implements Observer {
    private final CleaningController controller;

    public CleaningStatusView(CleaningController controller) {
        this.controller = controller;
    }

    @Override
    public void update(Object arg) {
        if (arg instanceof Float) {
            // Local progress update
            float progress = (Float) arg;
            System.out.printf("[Robot] Cleaning progress: %.1f%%\n", progress);
        } else if (arg instanceof RobotStatus) {
            // Full status update from robot
            RobotStatus status = (RobotStatus) arg;
            displayFullStatus(status);
        }
    }

    private void displayFullStatus(RobotStatus status) {
        System.out.println("\n=== ROBOT STATUS ===");
        System.out.printf("Progress: %d%%\n", status.getCleaningProgress());
        System.out.printf("Battery: %d%%\n", status.getBatteryLevel());
        System.out.println("Status: " + (status.isCleaning() ? "CLEANING" : "IDLE"));
        System.out.println("Dock: " + (status.isDocked() ? "DOCKED" : "UNDOCKED"));
        System.out.println("==================\n");
    }

    public void refresh() {
        controller.updateRobotStatusDisplay();
    }
}