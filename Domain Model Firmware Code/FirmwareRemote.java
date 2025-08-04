import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Write a description of class FirmwareRemote here.
 *
 * @Brian
 * @14/6
 */
public interface FirmwareRemote extends Remote 
{
    void registerObserver(RobotObserverRemote observer) throws RemoteException;
    void sendCommand(String commandText) throws RemoteException;
}
