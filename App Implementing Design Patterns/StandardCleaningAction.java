/**
 * A full-room default cleaning strategy.
 */

public class StandardCleaningAction implements CleaningAction {
    @Override
    public void execute(CleaningSession session) {
        System.out.println("Standard cleaning executed.");
    }
}

