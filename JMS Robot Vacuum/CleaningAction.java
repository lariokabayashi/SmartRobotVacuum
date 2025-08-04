
/**
 * STRATEGY (Strategy pattern)
 * ---------------------------------
 * Represents a pluggable cleaning algorithm.  Concrete
 * implementations execute their own logic and manipulate
 * the supplied {@link CleaningSession}.
 */
public interface CleaningAction {
    void execute(CleaningSession session);
}

