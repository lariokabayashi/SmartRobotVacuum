
/**
 * Write a description of class MobileAppClient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import serobjs.RobotStatus;

import javax.jms.*;
import java.util.concurrent.*;
import java.util.function.Consumer;  
import java.util.ArrayList;
import java.util.List;
import serobjs.RobotStatus;
import java.util.UUID;
import java.util.concurrent.*;

public class MobileAppCaller extends JMSClient implements RobotStatusInterface {
    private MessageProducer commandProducer;
    private MessageConsumer responseConsumer;
    private Destination responseQueue;
    private int messageID;
    private final ScheduledExecutorService executor;

    public MobileAppCaller(String queueName) throws JMSException {
        super(queueName);
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.messageID = 0;
    }

    @Override
    protected boolean connect() throws JMSException {
        if (!connected) {
            super.connect();
            try {
                // Create command producer
                Destination commandQueue = session.createQueue(destination);
                commandProducer = session.createProducer(commandQueue);
                commandProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            
                // Create temporary response queue
                responseQueue = session.createTemporaryQueue();
                responseConsumer = session.createConsumer(responseQueue);
            
                connected = true;
                System.out.println("Connected to queue: " + destination);
            } catch (JMSException e) {
                connected = false;
                throw e;
            }
        }
        return connected;
    }

    @Override
    public RobotStatus getCurrentStatus() throws JMSException {
        return sendRequest(RobotMessageKeys.GET_STATUS, RobotStatus.class);
    }

    @Override
    public boolean startCleaning() throws JMSException {
        return sendRequest(RobotMessageKeys.START_CLEANING, Boolean.class);
    }

    @Override
    public boolean stopCleaning() throws JMSException {
        return sendRequest(RobotMessageKeys.STOP_CLEANING, Boolean.class);
    }

    @Override
    public boolean returnToDock() throws JMSException {
        return sendRequest(RobotMessageKeys.RETURN_TO_DOCK, Boolean.class);
    }

    private <T> T sendRequest(RobotMessageKeys messageKey, Class<T> responseType) 
            throws JMSException {
        if (!connected) {
            connect();
        }
    
        try {
        // Create and send request
            TextMessage request = session.createTextMessage(); // Changed to TextMessage
            String correlationId = UUID.randomUUID().toString();
        
            request.setStringProperty("MessageType", messageKey.toString());
            request.setJMSReplyTo(responseQueue);
            request.setJMSCorrelationID(correlationId);
            request.setIntProperty("MessageID", ++messageID);
        
            System.out.println("Sending " + messageKey + " request (ID: " + messageID + ")");
            commandProducer.send(request);
        
            // Wait for response
            return listenForResponse(correlationId, responseType);
        } catch (JMSException e) {
            System.err.println("Error sending " + messageKey + " request: " + e.getMessage());
            throw e;
        }
    }
    
    private <T> T listenForResponse(String correlationId, Class<T> responseType) 
        throws JMSException {
        try {
            System.out.println("Waiting for response to " + correlationId);
            Message response = responseConsumer.receive(5000); // 5 second timeout
        
            if (response == null) {
                throw new JMSException("Timeout waiting for response to " + correlationId);
            }   
        
            // Verify correlation ID
            if (!correlationId.equals(response.getJMSCorrelationID())) {
                throw new JMSException("Correlation ID mismatch. Expected: " + 
                    correlationId + ", Received: " + response.getJMSCorrelationID());
            }
        
            // Process response based on expected type
            if (responseType == Boolean.class && response instanceof TextMessage) {
                String text = ((TextMessage) response).getText();
                System.out.println("Received boolean response: " + text);
                return responseType.cast(Boolean.valueOf(text));
            } 
            else if (responseType == RobotStatus.class && response instanceof ObjectMessage) {
                RobotStatus status = (RobotStatus) ((ObjectMessage) response).getObject();
                System.out.println("Received status response: " + status);
                return responseType.cast(status);
            }
        
            throw new JMSException("Unexpected response type: " + 
                response.getClass().getSimpleName());
            } catch (JMSException e) {
                System.err.println("Error receiving response: " + e.getMessage());
                throw e;
            }
        }

    @Override
    protected boolean close() throws JMSException {
        executor.shutdown();
        try {
            if (responseConsumer != null) responseConsumer.close();
            if (responseQueue != null) ((TemporaryQueue)responseQueue).delete();
            if (commandProducer != null) commandProducer.close();
        } finally {
            return super.close();
        }
    }

    @Override
    public void eot() throws JMSException {
        if (connected) {
            Message message = session.createMessage();
            message.setStringProperty("MessageType", RobotMessageKeys.EOT.toString());
            message.setIntProperty("MessageID", ++messageID);
            commandProducer.send(message);
            close();
        }
    }
}