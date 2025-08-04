/**
 * Represents the human user of the system.
 */
public class EndUser {
    private UserResponseHandler responseHandler;
    private UserNotifier notifier;
    private LogManager log;
    public void receiveNotification(String message) {}
    public void HandleLogs(String log) {}
}