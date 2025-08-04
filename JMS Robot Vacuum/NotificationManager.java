/**
 * VIEW/Helper – central place for toast/pop-up style alerts.
 * Also observes CleaningSession progress for quick status ticks.
 */

import javax.swing.*;
import javax.jms.*;
import java.util.Observable;
import serobjs.RobotStatus;

public class NotificationManager extends JMSClient implements NotificationInterface, Observer {
    private static final String STATUS_TOPIC = "RobotStatus.T";
    private static final String ALERT_TOPIC = "RobotAlerts.T";
    
    private MessageConsumer statusConsumer;
    private MessageConsumer alertConsumer;
    
    public NotificationManager() throws JMSException {
        super("notifications");
        initializeConsumers();
    }

    private void initializeConsumers() throws JMSException {
        Destination statusDestination = session.createTopic(STATUS_TOPIC);
        Destination alertDestination = session.createTopic(ALERT_TOPIC);
        
        statusConsumer = session.createConsumer(statusDestination);
        alertConsumer = session.createConsumer(alertDestination);
        
        statusConsumer.setMessageListener(this::handleStatusMessage);
        alertConsumer.setMessageListener(this::handleAlertMessage);
    }

    // Fixed Observable update method
    @Override
    public void update(Object arg) {
        if (arg instanceof Float) {
            float progress = (Float) arg;
            int percent = Math.round(progress * 100);
            System.out.println("Cleaning progress: " + percent + "%");
            if (percent == 100) {
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(null, "Cleaning complete!"));
            }
        }
    }

    private void handleStatusMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                RobotStatus status = (RobotStatus) ((ObjectMessage) message).getObject();
                notifyStatusChange(status);
            }
        } catch (JMSException e) {
            try
            {
                showErrorAlert("Error processing status: " + e.getMessage());
            }
            catch (JMSException jmse)
            {
                jmse.printStackTrace();
            }
        }
    }

    private void handleAlertMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                if ("LOW_BATTERY".equals(text)) {
                    notifyLowBattery(0); // Default level for simple notification
                } else if ("CLEANING_COMPLETE".equals(text)) {
                    notifyCleaningComplete();
                }
            }
        } catch (JMSException e) {
            try
            {
                showErrorAlert("Error processing alert: " + e.getMessage());
            }
            catch (JMSException jmse)
            {
                jmse.printStackTrace();
            }
        }
    }

    public void showErrorAlert(String message) throws JMSException{
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE));
    }

    @Override
    public void notifyStatusChange(RobotStatus status) throws JMSException {
        /*
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(null, 
                String.format("Status Update:\nProgress: %d%%\nBattery: %d%%",
                    status.getCleaningProgress(),
                    status.getBatteryLevel()),
                "Robot Status",
                JOptionPane.INFORMATION_MESSAGE));*/
    }

    @Override
    public void notifyLowBattery(int batteryLevel) throws JMSException {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(null, 
                "Battery critically low!\nPlease return robot to dock.", 
                "Low Battery", 
                JOptionPane.WARNING_MESSAGE));
    }

    @Override
    public void notifyCleaningComplete() throws JMSException {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(null, "Cleaning complete!", "Success", JOptionPane.INFORMATION_MESSAGE));
    }

    @Override
    public void returnToDock() throws JMSException {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(null, "Returning to dock", "Action", JOptionPane.INFORMATION_MESSAGE));
    }

    @Override
    protected boolean close() throws JMSException {
        if (statusConsumer != null) statusConsumer.close();
        if (alertConsumer != null) alertConsumer.close();
        return super.close();
    }
}