
/**
 * A quick spot-clean strategy for small messes.
 */
public class SpotCleaningAction implements CleaningAction {
    @Override
    public void execute(CleaningSession session) {
        try {
            // Simulate spot cleaning progress
            for (int i = 0; i <= 100; i += 20) {
                session.setProgress(i);
                Thread.sleep(500); // Faster than standard cleaning
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
