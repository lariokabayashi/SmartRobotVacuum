import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Write a description of class RobotObserverRemote here.
 *
 * @Brian
 * @14/6
 */
public interface RobotObserverRemote extends Remote 
{
    void update() throws RemoteException;
    void updateMap(Map output, float percent) throws RemoteException;
    void noBattery() throws RemoteException;
}
