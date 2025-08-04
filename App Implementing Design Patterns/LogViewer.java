/**
 * VIEW – shows entries from {@link SessionLog}.
 */
import java.util.List;

public class LogViewer implements Observer {
    @Override public void update(Object arg) {
        if (arg instanceof List<?>) show((List<SessionEntry>) arg);
    }
    public void show(List<SessionEntry> entries) { /* table view */ }
}

