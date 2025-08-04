/**
 * CONTROLLER – orchestrates cleaning workflow.
 * Delegates to {@link CleaningSession}, {@link PreferenceManager}, {@link Logger}.
 */
import java.util.*;

public class CleaningController {
    private final CleaningSession           session;
    private final PreferenceManager         prefMgr;
    private final Logger                    logger;
    private final List<NotificationManager> notifiers = new ArrayList<>();

    public CleaningController(CleaningSession s, PreferenceManager pm, Logger lg) {
        this.session = s;
        this.prefMgr = pm;
        this.logger  = lg;
    }

    public void selectAction(CleaningAction a) { session.setCurrentAction(a); }
    public void startCleaning()                { session.executeAction();     }

    public void forwardPreference(String room, CleaningPreference p) {
        prefMgr.savePreference(room, p);
    }
    public void notifyCompletion() {
        for (NotificationManager n : notifiers) n.showAlert(AlertType.INFO);
    }
    public void sendLog(String msg) { logger.log(msg); }
}

