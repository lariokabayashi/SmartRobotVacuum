
/**
 * Write a description of class MobileAppResponder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import serobjs.RobotStatus;
import javax.jms.*;
import java.util.function.Consumer;  
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import serobjs.RobotStatus;

import serobjs.RobotStatus;
import javax.jms.*;
import serobjs.RobotStatus;
import javax.jms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MobileAppResponder extends JMSClient implements NotificationHandler {
    private MessageConsumer statusConsumer;
    private MessageConsumer alertConsumer;
    private final List<Consumer<RobotStatus>> statusListeners = new ArrayList<>();
    private final List<Consumer<String>> errorListeners = new ArrayList<>();

    public MobileAppResponder(String statusTopic, String alertTopic) throws JMSException {
        super("notifications");
        initializeConsumers(statusTopic, alertTopic);
    }

    private void initializeConsumers(String statusTopic, String alertTopic) throws JMSException {
        Destination statusDestination = session.createTopic(statusTopic);
        Destination alertDestination = session.createTopic(alertTopic);
        
        statusConsumer = session.createConsumer(statusDestination);
        alertConsumer = session.createConsumer(alertDestination);
        
        statusConsumer.setMessageListener(this::handleStatusMessage);
        alertConsumer.setMessageListener(this::handleAlertMessage);
    }

    private void handleStatusMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                RobotStatus status = (RobotStatus) ((ObjectMessage) message).getObject();
                notifyStatusListeners(status);
            }
        } catch (JMSException e) {
            notifyErrorListeners("Error processing status message: " + e.getMessage());
        }
    }

    private void handleAlertMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                if (text.startsWith("LOW_BATTERY:")) {
                    int batteryLevel = Integer.parseInt(text.substring(12));
                    notifyLowBatteryListeners(batteryLevel);
                } else if (text.equals("CLEANING_COMPLETE")) {
                    notifyCompletionListeners();
                } else if (text.startsWith("ERROR:")) {
                    notifyErrorListeners(text.substring(6));
                }
            }
        } catch (Exception e) {
            notifyErrorListeners("Error processing alert message: " + e.getMessage());
        }
    }

    private void notifyStatusListeners(RobotStatus status) {
        statusListeners.forEach(listener -> listener.accept(status));
    }

    private void notifyLowBatteryListeners(int batteryLevel) {
        errorListeners.forEach(listener -> 
            listener.accept("Low battery alert: " + batteryLevel + "% remaining"));
    }

    private void notifyCompletionListeners() {
        errorListeners.forEach(listener -> 
            listener.accept("Cleaning completed successfully"));
    }

    private void notifyErrorListeners(String error) {
        errorListeners.forEach(listener -> listener.accept(error));
    }

    @Override
    public void addStatusListener(Consumer<RobotStatus> listener) {
        if (listener != null && !statusListeners.contains(listener)) {
            statusListeners.add(listener);
        }
    }

    @Override
    public void removeStatusListener(Consumer<RobotStatus> listener) {
        statusListeners.remove(listener);
    }

    @Override
    public void addErrorListener(Consumer<String> listener) {
        if (listener != null && !errorListeners.contains(listener)) {
            errorListeners.add(listener);
        }
    }

    @Override
    public void removeErrorListener(Consumer<String> listener) {
        errorListeners.remove(listener);
    }

    @Override
    protected boolean close() throws JMSException {
        if (statusConsumer != null) statusConsumer.close();
        if (alertConsumer != null) alertConsumer.close();
        statusListeners.clear();
        errorListeners.clear();
        return super.close();
    }
}