import serobjs.RobotStatus;
import javax.jms.JMSException;

/**
 * Defines the robot command interface for client-to-server communication.
 * Implemented by the MobileAppCaller (client) and handled by RobotResponder (server).
 */
public interface RobotStatusInterface {
    /**
     * Gets the complete current status of the robot
     * @return RobotStatus object containing all status information
     * @throws JMSException if communication fails
     */
    RobotStatus getCurrentStatus() throws JMSException;
    
    /**
     * Starts the cleaning process
     * @return true if command was accepted, false otherwise
     * @throws JMSException if communication fails
     */
    boolean startCleaning() throws JMSException;
    
    /**
     * Stops the cleaning process
     * @return true if command was accepted, false otherwise
     * @throws JMSException if communication fails
     */
    boolean stopCleaning() throws JMSException;
    
    /**
     * Commands the robot to return to its docking station
     * @return true if command was accepted, false otherwise
     * @throws JMSException if communication fails
     */
    boolean returnToDock() throws JMSException;
    
    /**
     * Sets the cleaning mode (e.g., "standard", "deep", "spot")
     * @param mode The cleaning mode to set
     * @return true if mode was accepted, false otherwise
     * @throws JMSException if communication fails
     
    boolean setCleaningMode(String mode) throws JMSException;*/
    
    /**
     *  eot sends an EndOfTransmission and quits the connection
     */
    void eot() throws JMSException;
}