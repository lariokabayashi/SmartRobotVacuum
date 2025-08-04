/**
 * Quick console test tying everything together.
 */
public class Main {
    public static void main(String[] args) {
        // Core components
        CleaningSession       session    = new CleaningSession();
        RoomPreferenceStorage prefs      = new RoomPreferenceStorage();
        PreferenceManager     prefMgr    = new PreferenceManager(prefs);
        Logger                logger     = new Logger();

        CleaningController controller = new CleaningController(session, prefMgr, logger);

        // Attach a live progress view
        CleaningStatusView statusView = new CleaningStatusView();
        session.registerObserver(statusView);

        // Select strategy & run once
        controller.selectAction(new StandardCleaningAction());
        controller.startCleaning();  // triggers observer update -> console output
    }
}

