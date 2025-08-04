
/**
 * Single line in the session log.
 * Extend with timestamp, status, message, etc.
 */
public class SessionEntry {
    private final long   timestamp = System.currentTimeMillis();
    private       String message   = "";

    public SessionEntry() { }
    public SessionEntry(String msg) { this.message = msg; }

    public long   getTimestamp() { return timestamp; }
    public String getMessage()   { return message;   }
    public void   setMessage(String m) { message = m; }

    @Override public String toString() {
        return "[" + timestamp + "] " + message;
    }
}

