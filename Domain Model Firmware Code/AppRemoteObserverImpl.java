import java.rmi.RemoteException;

/**
 * Write a description of class AppRemoteObserverImpl here.
 *
 * @Brian
 * @14/6
 */
public class AppRemoteObserverImpl implements AppRemoteObserver 
{
    private final RobotObserverRemote remote;

    public AppRemoteObserverImpl(RobotObserverRemote remote) {
        this.remote = remote;
    }

    @Override
    public void update() {
        try {
            remote.update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMap(Map output, float percent) {
        try {
            remote.updateMap(output, percent);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void noBattery() {
        try {
            remote.noBattery();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
