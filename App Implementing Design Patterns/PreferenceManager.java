/**
 * CONTROLLER – thin wrapper around {@link RoomPreferenceStorage}.
 */
public class PreferenceManager {
    private final RoomPreferenceStorage storage;
    public PreferenceManager(RoomPreferenceStorage s){ this.storage = s; }

    public void savePreference(String room, CleaningPreference p){
        storage.setPreference(room, p);
    }
    public CleaningPreference loadPreference(String room){
        return storage.getPreference(room);
    }
}

