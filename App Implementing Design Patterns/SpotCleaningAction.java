
/**
 * A quick spot-clean strategy for small messes.
 */
public class SpotCleaningAction implements CleaningAction {
    @Override
    public void execute(CleaningSession session) {
        System.out.println("Spot cleaning executed.");
        session.setProgress(session.getProgress() + 5f);
    }
}

