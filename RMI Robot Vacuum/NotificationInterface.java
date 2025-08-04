
/**
 * Write a description of interface RobotCallbackInterface here.
 *
 * @author Larissa
 * @version 26/06
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotificationInterface extends Remote {
    void notifyStatusChange(RobotStatus status) throws RemoteException;
    void notifyLowBattery() throws RemoteException;
    void notifyCleaningComplete() throws RemoteException;
    void returnToDock() throws RemoteException;
}