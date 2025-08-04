
/**
 * Write a description of class RobotCaller here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import serobjs.RobotStatus;
import javax.jms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.jms.*;
import serobjs.RobotStatus;
import javax.jms.*;
import serobjs.RobotStatus;
import java.util.Objects;

public class RobotCaller extends JMSClient implements NotificationInterface {
    private MessageProducer statusProducer;
    private MessageProducer alertProducer;
    private final String statusTopic;
    private final String alertTopic;

    public RobotCaller(String statusTopic, String alertTopic) throws JMSException {
        super("notifications");
        this.statusTopic = Objects.requireNonNull(statusTopic);
        this.alertTopic = Objects.requireNonNull(alertTopic);
        initializeProducers();
    }

    private void initializeProducers() throws JMSException {
        Destination statusDestination = session.createTopic(statusTopic);
        Destination alertDestination = session.createTopic(alertTopic);
        
        statusProducer = session.createProducer(statusDestination);
        statusProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        
        alertProducer = session.createProducer(alertDestination);
        alertProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    @Override
    public void notifyStatusChange(RobotStatus status) throws JMSException {
        ObjectMessage message = session.createObjectMessage(status);
        statusProducer.send(message);
    }

    @Override
    public void notifyLowBattery(int batteryLevel) throws JMSException {
        TextMessage message = session.createTextMessage();
        message.setText("LOW_BATTERY:" + batteryLevel);
        alertProducer.send(message);
    }

    @Override
    public void notifyCleaningComplete() throws JMSException {
        TextMessage message = session.createTextMessage("CLEANING_COMPLETE");
        alertProducer.send(message);
    }
    
    @Override
    public void returnToDock() throws JMSException {
        TextMessage message = session.createTextMessage("RETURN_TO_DOCK");
        alertProducer.send(message);
    }

    public void notifyError(String errorMessage) throws JMSException {
        TextMessage message = session.createTextMessage("ERROR:" + errorMessage);
        alertProducer.send(message);
    }

    @Override
    protected boolean close() throws JMSException {
        if (statusProducer != null) statusProducer.close();
        if (alertProducer != null) alertProducer.close();
        return super.close();
    }
}