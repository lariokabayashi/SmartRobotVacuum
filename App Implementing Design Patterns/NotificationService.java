
/**
 * Write a description of interface NotificationService here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotificationService extends Remote {
    /**
     * Notifies the app that the robot is returning to the dock (e.g., due to low battery).
     */
    void notifyReturnToDock() throws RemoteException;

    /**
     * Pushes a cleaning progress update to the app.
     * @param progress Current cleaning percentage
     */
    void pushProgressUpdate(float progress) throws RemoteException;
}