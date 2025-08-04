
/**
 * Write a description of class RobotController here.
 *
 * @author Larissa
 * @version 26/06
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RobotController extends UnicastRemoteObject implements RobotStatusInterface {
    private RobotSystem robotSystem;
    private List<NotificationInterface> callbacks = new ArrayList<>();
    
    public RobotController() throws RemoteException {
        super();
        robotSystem = RobotSystem.getInstance();
        robotSystem.registerObserver(new RobotObserver() {
            @Override
            public void update() {
                try {
                    notifyCallbacks();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void notifyCallbacks() throws RemoteException {
        RobotStatus status = robotSystem.getStatus();
        for (NotificationInterface callback : callbacks) {
            try {
                callback.notifyStatusChange(status);
                
                // Check for specific conditions
                if (status.getBatteryLevel() < 15) {
                    callback.notifyLowBattery();
                }
                if (status.getCleaningProgress() >= 100) {
                    callback.notifyCleaningComplete();
                }
            } catch (RemoteException e) {
                // Client may have disconnected, remove from list
                callbacks.remove(callback);
            }
        }
    }
    
    @Override
    public RobotStatus getStatus() throws RemoteException {
        return robotSystem.getStatus();
    }


    @Override
    public int getCleaningProgress() throws RemoteException {
        return robotSystem.getCleaningProgress();
    }

    @Override
    public int getBatteryLevel() throws RemoteException {
        return Battery.getInstance().getBattery(); 
    }


    @Override
    public boolean startCleaning() throws RemoteException {
        System.out.println("Cleaning started.");
        return robotSystem.startCleaning();
    }

    @Override
    public boolean stopCleaning() throws RemoteException {
        System.out.println("Cleaning stopped.");
        return robotSystem.stopCleaning();
    }
    
    @Override
    public void registerCallback(NotificationInterface callback) throws RemoteException {
        if (!callbacks.contains(callback)) {
            callbacks.add(callback);
        }
    }
    
    @Override
    public void unregisterCallback(NotificationInterface callback) throws RemoteException {
        callbacks.remove(callback);
    }
}
