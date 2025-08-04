/**
 * A full-room default cleaning strategy.
 */

public class StandardCleaningAction implements CleaningAction {
    @Override
    public void execute(CleaningSession session) {
        try {
            // Simulate standard cleaning progress
            for (int i = 0; i <= 100; i += 10) {
                session.setProgress(i);
                Thread.sleep(1000); // Simulate time passing
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

