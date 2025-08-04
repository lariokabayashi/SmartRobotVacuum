/**
 * VIEW/Helper – central place for toast/pop-up style alerts.
 * Also observes CleaningSession progress for quick status ticks.
 */
import javax.swing.JOptionPane;

public class NotificationManager implements Observer {
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
    public void update(float p) { display(); }
    public void display() { System.out.println("Notification area refreshed"); }
    public void showAlert(AlertType t) { System.out.println("ALERT: " + t); }
    public void returnToDock() {
        System.out.println("Triggering return to dock UI...");
        javax.swing.SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Return the robot to dock.", "Action Required", JOptionPane.INFORMATION_MESSAGE);
        });
}
}
