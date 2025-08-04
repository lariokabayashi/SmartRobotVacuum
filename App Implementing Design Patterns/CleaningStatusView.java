/**
 * VIEW – live progress bar / label.
 * Observes {@link CleaningSession}.
 */
public class CleaningStatusView implements Observer {

    @Override
    public void update(Object arg) {
        if (arg instanceof Float p) {
            showProgress(p);        // <-- different name, no recursion
        }
    }

    /** Paint or print the current progress value. */
    private void showProgress(float progress) {
        System.out.printf("Progress: %.1f%%%n", progress);
    }
}