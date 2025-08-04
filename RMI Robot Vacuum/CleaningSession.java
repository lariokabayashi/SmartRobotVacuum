/**
 * MODEL – CleaningSession
 * ---------------------------------
 * Holds current progress and active {@link CleaningAction}.
 * Implements {@link Subject} so UI widgets (views) can observe
 * progress changes.  Acts as the *Context* for Strategy.
 */
import java.util.ArrayList;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class CleaningSession implements Subject {
    private CleaningAction currentAction;
    private final List<Observer> observers = new ArrayList<>();
    private RobotStatusInterface robotStatus;

    public void setRobotStatusInterface(RobotStatusInterface robotStatus) {
        this.robotStatus = robotStatus;
    }
    
    public void setProgress(float progress) {
        notifyObservers(progress);
    }

    public void setCurrentAction(CleaningAction a) { this.currentAction = a; }

    public void executeAction() {
        if (currentAction != null) currentAction.execute(this);
    }

    /* --------------- Subject plumbing --------------- */
    @Override public void registerObserver(Observer o) { observers.add(o); }
    @Override public void removeObserver(Observer o)   { observers.remove(o); }
    @Override public void notifyObservers() {
            System.err.println("Warning: notifyObservers() called without progress.");
    }
    public void notifyObservers(float progress) {
        // First update local observers
        for (Observer o : observers) {
            o.update(progress);
        }
        
        // Then update robot status if connected
        if (robotStatus != null) {
            try {
                // This would update the actual robot's status
                robotStatus.getStatus().setCleaningProgress((int)progress);
            } catch (RemoteException e) {
                System.err.println("Failed to update robot status: " + e.getMessage());
            }
        }
    }
}
