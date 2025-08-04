
/**
 * Write a description of interface RobotStatusInterface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RobotStatusInterface extends Remote {
    int getCleaningProgress() throws RemoteException;
    int getBatteryLevel() throws RemoteException;
    boolean startCleaning() throws RemoteException;
    boolean stopCleaning() throws RemoteException;
    RobotStatus getStatus() throws RemoteException;
    void registerCallback(NotificationInterface callback) throws RemoteException;
    void unregisterCallback(NotificationInterface callback) throws RemoteException;
}