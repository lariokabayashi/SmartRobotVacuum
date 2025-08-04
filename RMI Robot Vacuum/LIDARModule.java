    
/**
 * Represents a specific SLAMModule that uses LIDAR sensors to detect 
 * obstacles. It processes LIDAR distance data and updates the 
 * PathChecker accordingly.
 *
 * @author Brian
 * @version 30/5
 */
public class LIDARModule extends SLAMModule
{
    /**
     * Constructor for objects of class LIDARModule.
     * 
     * @param position the position of this sensor in the array in PathChecker.
     */
    public LIDARModule(int position)
    {
        super(position);
    }

    /**
     * Simulates receiving new distance data from the LIDAR sensor
     * and updates the PathChecker with this information.
     * 
     * @param distance The newly detected distance to the nearest obstacle.
     */
    public void detectObstacle(int distance)
    {
        updateDistance(distance);
    }
}
