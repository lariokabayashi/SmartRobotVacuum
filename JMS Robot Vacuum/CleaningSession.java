/**
 * MODEL – CleaningSession
 * ---------------------------------
 * Holds current progress and active {@link CleaningAction}.
 * Implements {@link Subject} so UI widgets (views) can observe
 * progress changes.  Acts as the *Context* for Strategy.
 */
import java.util.ArrayList;
import java.util.List;
import serobjs.RobotStatus;

public class CleaningSession implements Subject {
    
    private CleaningAction currentAction;
    private final List<Observer> observers = new ArrayList<>();
    private CleaningStatusView statusView;

    public void setStatusView(CleaningStatusView view) {
        this.statusView = view;
    }

    public void setProgress(int progress) {
        if (statusView != null) {
            //.updateProgress(progress);
        }
    }

    public void notifyStatus(RobotStatus status) {
        if (statusView != null) {
            //statusView.updateStatus(status);
        }
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
        for (Observer o : observers) {
            o.update(progress);
        }
    }
}
