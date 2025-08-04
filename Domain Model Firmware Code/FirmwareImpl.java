import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

/**
 * Write a description of class FirmwareImpl here.
 *
 * @Brian
 * @14/6
 */
public class FirmwareImpl extends UnicastRemoteObject implements FirmwareRemote {
    public FirmwareImpl() throws RemoteException {
        super();
    }

    @Override
    public void registerObserver(RobotObserverRemote remoteObserver) throws RemoteException {
        AppRemoteObserver observer = new AppRemoteObserverImpl(remoteObserver);
        AppInput.getInstance().registerRemoteObserver(observer);
    }

    @Override
    public void sendCommand(String commandText) throws RemoteException {
        AppOutput ao = new AppOutput();
        ao.handleCommand(commandText);
    }
}