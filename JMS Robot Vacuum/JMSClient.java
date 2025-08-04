import java.util.ArrayList;
import java.util.Arrays;

import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Generic JMSClient for Openwire ActiveMQ.
 *
 * @author Wolfgang Renz
 * @version May 2020, Dec. 2024
 */
public abstract class JMSClient
{
    protected Session session;
    protected String destination;
    protected boolean connected;

    protected ActiveMQConnectionFactory factory;
    private Connection connection;
    private String user, password;

    /**
     * Constructor for objects of class JMSClient
     */
    

    public JMSClient(String host, int port, String user, String password, 
    String dest) throws JMSException
    {
        factory = new ActiveMQConnectionFactory("tcp://" + host + ":" + port);
        setTrustedPackages();
        this.user= user;
        this.password= password;
        this.destination= dest;
        connected= connect();
    }

    public void setTrustedPackages()
    {
        factory.setTrustedPackages(new ArrayList(
                Arrays.asList(
                    "serobjs,org.apache.activemq.test,org.apache.camel.test,java.util,java.lang"
                    .split(",")
                )
            ));
    }

    /**
     * Default constructor
     */
    public JMSClient(String destination) throws JMSException
    {
        this("192.168.244.129", 61616, "admin", "password", destination);
    }

    protected boolean connect() throws JMSException
    {    
        connection = factory.createConnection(user, password);
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        return true;
    }

    protected boolean close() throws JMSException
    {
        connection.close();
        connected= false;
        return connected;
    }
}
