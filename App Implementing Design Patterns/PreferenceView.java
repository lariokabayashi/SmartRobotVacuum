/**
 * VIEW – shows and edits per-room preferences.
 * Observes {@link RoomPreferenceStorage}.
 */
import java.util.Map;

public class PreferenceView implements Observer {
    @Override public void update(Object arg) {
        if (arg instanceof Map) display((Map<String,CleaningPreference>) arg);
    }
    public void display(Map<String,CleaningPreference> prefs) { /* GUI code */ }
    public void update(String room, CleaningPreference p)     { /* refresh cell */ }
    public void sendUserInput(String room, CleaningPreference p){ /* -> controller */ }
}

