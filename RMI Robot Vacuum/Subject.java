
/**
 * SUBJECT (Observer pattern)
 * ---------------------------------
 * A model element that can be observed.  Concrete Subjects
 * (e.g., CleaningSession, RoomPreferenceStorage, SessionLog …)
 * call {@link #notifyObservers()} whenever their state changes.
 */

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

