/**
 * SINGLETON + SUBJECT – central log of user & robot events.
 * Access globally via {@link #getInstance()}.
 */
import java.util.*;

public class SessionLog implements Subject {
    private static SessionLog instance;
    private final List<SessionEntry> entries = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    private SessionLog() {}

    public static synchronized SessionLog getInstance() {
        if (instance == null) instance = new SessionLog();
        return instance;
    }

    public void addEntry(SessionEntry e) {
        entries.add(e);
        notifyObservers();
    }
    public List<SessionEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    @Override public void registerObserver(Observer o) { observers.add(o); }
    @Override public void removeObserver(Observer o)   { observers.remove(o); }
    @Override public void notifyObservers() {
        for (Observer o : observers) o.update(Collections.unmodifiableList(entries));
    }
}
