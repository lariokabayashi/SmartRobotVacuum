import serobjs.RobotStatus;
import javax.jms.*;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class RobotResponder extends JMSClient implements RobotStatusInterface {
    private final RobotSystem robotSystem;
    private final Map<RobotMessageKeys, MessageConsumer> consumers;
    private MessageProducer replyProducer;
    private MessageConsumer eotConsumer;
    
    public RobotResponder(RobotSystem robotSystem, String queueName) throws JMSException {
        this(robotSystem, "localhost", 61616, "admin", "password", queueName);
    }

    public RobotResponder(RobotSystem robotSystem, String host, int port, 
                         String user, String password, String queueName) throws JMSException {
        super(host, port, user, password, queueName);
        this.robotSystem = Objects.requireNonNull(robotSystem, "RobotSystem cannot be null");
        this.consumers = new EnumMap<>(RobotMessageKeys.class);
        initializeResources();
    }

    private void initializeResources() throws JMSException {
        // Initialize reply producer first
        this.replyProducer = session.createProducer(null);
        this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        
        // Initialize command queue consumers
        Destination commandQueue = session.createQueue(destination);
        initializeCommandConsumers(commandQueue);
    }

    private void initializeCommandConsumers(Destination queue) throws JMSException {
        // Initialize standard command consumers
        createAndStoreConsumer(queue, RobotMessageKeys.GET_STATUS);
        createAndStoreConsumer(queue, RobotMessageKeys.START_CLEANING);
        createAndStoreConsumer(queue, RobotMessageKeys.STOP_CLEANING);
        createAndStoreConsumer(queue, RobotMessageKeys.RETURN_TO_DOCK);
        
        // Initialize EOT consumer separately
        this.eotConsumer = session.createConsumer(
            queue, 
            String.format("MessageType = '%s'", RobotMessageKeys.EOT.toString())
        );
    }

    private void createAndStoreConsumer(Destination queue, RobotMessageKeys key) throws JMSException {
        MessageConsumer consumer = session.createConsumer(
            queue,
            String.format("MessageType = '%s'", key.toString())
        );
        this.consumers.put(key, consumer);
    }

    public void listen() throws JMSException {
        Objects.requireNonNull(consumers, "Command consumers not initialized");
        Objects.requireNonNull(eotConsumer, "EOT consumer not initialized");
        
        // Setup listeners for standard commands
        consumers.forEach((key, consumer) -> {
            try {
                consumer.setMessageListener(createCommandListener(key));
                System.out.println("Listening for " + key + " commands...");
            } catch (JMSException e) {
                System.err.println("Failed to set listener for " + key + ": " + e.getMessage());
            }
        });
        
        // Setup EOT listener
        eotConsumer.setMessageListener(msg -> {
            try {
                System.out.println("EOT command received");
                eot();
            } catch (JMSException e) {
                System.err.println("Error handling EOT: " + e.getMessage());
            }
        });
        System.out.println("Listening for EOT commands...");
    }

private MessageListener createCommandListener(RobotMessageKeys key) {
    return msg -> {
        try {
            System.out.println(key + " command received");
            Destination replyTo = msg.getJMSReplyTo();
            if (replyTo != null) {
                Message response = createResponse(key, msg);
                // THIS IS THE FIX
                response.setJMSCorrelationID(msg.getJMSCorrelationID());
                replyProducer.send(replyTo, response);
            }
        } catch (JMSException e) {
            System.err.println("Error handling " + key + ": " + e.getMessage());
        }
    };
}

    private Message createResponse(RobotMessageKeys key, Message request) throws JMSException {
        switch (key) {
            case GET_STATUS:
                return session.createObjectMessage(getCurrentStatus());
            case START_CLEANING:
                return session.createTextMessage(String.valueOf(startCleaning()));
            case STOP_CLEANING:
                return session.createTextMessage(String.valueOf(stopCleaning()));
            case RETURN_TO_DOCK:
                return session.createTextMessage(String.valueOf(returnToDock()));
            default:
                return session.createTextMessage("ERROR: Unsupported command");
        }
    }

    @Override
    public RobotStatus getCurrentStatus() {
        return robotSystem.getStatus();
    }

    @Override
    public boolean startCleaning() {
        return robotSystem.startCleaning();
    }

    @Override
    public boolean stopCleaning() {
        return robotSystem.stopCleaning();
    }

    @Override
    public boolean returnToDock() {
        return robotSystem.returnToDock();
    }

    @Override
    public void eot() throws JMSException {
        System.out.println("Shutting down responder...");
        close();
    }

    @Override
    protected boolean close() throws JMSException {
        try {
            if (replyProducer != null) replyProducer.close();
            if (eotConsumer != null) eotConsumer.close();
            consumers.values().forEach(consumer -> {
                try {
                    consumer.close();
                } catch (JMSException e) {
                    System.err.println("Error closing consumer: " + e.getMessage());
                }
            });
            consumers.clear();
        } finally {
            return super.close();
        }
    }
}