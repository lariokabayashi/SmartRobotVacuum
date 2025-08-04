/**
 * CONTROLLER – lightweight façade over the singleton {@link SessionLog}.
 */
public class Logger {
    private final SessionLog log = SessionLog.getInstance();
    public void log(String event){
        log.addEntry(new SessionEntry()); // TODO attach message & data
    }
}
