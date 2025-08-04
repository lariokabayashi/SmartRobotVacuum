import serobjs.RobotStatus;
import javax.jms.JMSException;
import java.util.function.Consumer;
/**
 * Defines the notification interface for server-to-client communication.
 * Implemented by the RobotCaller (server) and handled by AppResponder (client).
 */

public interface NotificationInterface {
    void notifyStatusChange(RobotStatus status) throws JMSException;
    void notifyLowBattery(int batteryLevel) throws JMSException;
    void notifyCleaningComplete() throws JMSException;
    void returnToDock() throws JMSException;
}
