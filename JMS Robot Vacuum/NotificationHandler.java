
/**
 * Write a description of class NotificationHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.jms.JMSException;
import serobjs.RobotStatus;
import java.util.function.Consumer;

public interface NotificationHandler {
    void addStatusListener(Consumer<RobotStatus> listener);
    void removeStatusListener(Consumer<RobotStatus> listener);
    void addErrorListener(Consumer<String> listener);
    void removeErrorListener(Consumer<String> listener);
}