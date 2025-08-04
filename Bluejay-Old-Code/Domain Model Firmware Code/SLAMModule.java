/**
 * Abstract class representing the module for all sensor types of the
 * SLAM obstacle detection system. It processes the sensor data and
 * sends it to the PathChecker to be incorporated into one current
 * real-time check of obstacles around the robot.
 * 
 * 
 * @author Brian
 * @version 14/05
 */
public abstract class SLAMModule
{
    private int distance;
    private int position;

    /**
     * Constructor for objects of class SLAMModule
     * 
     * @param pc The PathChecker object to send distance data to.
     */
    public SLAMModule(int position)
    {
        this.position = position;
        distance = 0;
    }

    /**
     * Retreives most recent distance to obstacle detected.
     *
     * @return distance the distance to an obstacle
     */
    public int getData()
    {
        return distance;
    }
    
    /**
     * When the most recent distance to obstacle is detected sends it to
     * the PartChecker class for real-time data
     *
     * @param  newD    a new distance variable from sensors
     * @return confirmation
     */
    public int updateDistance(int newD)
    {
        this.distance = newD;
        PathChecker.getInstance().updateData(this.position, newD);
        return 0;
    }
    
    /**
     * A getter for position.
     */
    public int getPos() {
        return position;
    }
}
