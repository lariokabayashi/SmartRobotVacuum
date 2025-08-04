/**
 * OBSERVER (Observer pattern)
 * ---------------------------------
 * Any view/controller/helper that wants push-style updates
 * from a {@link Subject}.  The single update argument is
 * intentionally generic (Object) so each Subject can pass the
 * most convenient payload: Float, Map, List, etc.
 */
public interface Observer {
    void update(Object arg);
}
