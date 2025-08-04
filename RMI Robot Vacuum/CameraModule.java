
/**
 * Represents a specific SLAMModule that uses a Camera sensor to 
 * detect obstacles. It processes camera-based distance data and updates
 * the PathChecker accordingly.
 *
 * @author Brian
 * @version 14/5
 */
public class CameraModule extends SLAMModule
{
    /**
     * Constructor for objects of class CameraModule.
     * 
     * @param position the position of this sensor in the array in PathChecker.
     */
    public CameraModule(int position)
    {
        super(position);
    }

    /**
     * Simulates receiving new distance data from the Camera sensor
     * and updates the PathChecker with this information.
     * 
     * @param distance The newly detected distance to the nearest obstacle.
     */
    public void detectObstacle(int distance)
    {
        updateDistance(distance);
    }
}
