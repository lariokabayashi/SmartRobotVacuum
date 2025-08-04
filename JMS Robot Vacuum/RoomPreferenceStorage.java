/**
 * MODEL – stores per-room {@link CleaningPreference}s.
 * Observable so a PreferenceView can refresh automatically.
 */
import java.util.*;
import java.util.Map;

public class RoomPreferenceStorage implements Subject {
    private final Map<String,CleaningPreference> prefs = new HashMap<>();
    private final List<Observer> observers = new ArrayList<>();

    public CleaningPreference getPreference(String roomId) { return prefs.get(roomId); }

    public void setPreference(String roomId, CleaningPreference pref) {
        prefs.put(roomId, pref);
        notifyObservers();
    }

    @Override public void registerObserver(Observer o) { observers.add(o); }
    @Override public void removeObserver(Observer o)   { observers.remove(o); }
    @Override public void notifyObservers() {
        for (Observer o : observers) o.update(Collections.unmodifiableMap(prefs));
    }
}
