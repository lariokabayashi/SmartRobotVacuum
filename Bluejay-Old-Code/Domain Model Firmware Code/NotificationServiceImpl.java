import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
/**
 * Write a description of class NotificationServiceImpl here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class NotificationServiceImpl extends UnicastRemoteObject implements NotificationService {

    private NotificationManager notificationManager;

    public NotificationServiceImpl(NotificationManager notificationManager) throws RemoteException {
        this.notificationManager = notificationManager;
    }

    @Override
    public void notifyReturnToDock() throws RemoteException {
        notificationManager.returnToDock();
    }
}
