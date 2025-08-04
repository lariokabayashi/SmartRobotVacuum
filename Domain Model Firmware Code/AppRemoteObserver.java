import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AppRemoteObserver extends Remote 
{
    void update() throws RemoteException;
    void updateMap(Map output, float percent) throws RemoteException;
    void noBattery() throws RemoteException;
}
