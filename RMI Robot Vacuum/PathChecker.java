import java.util.ArrayList;
import java.util.Arrays;

/**
 * PathChecker receives sensor data from SLAMModules, processes it and 
 * sends the compiled data to the Navigator for movement decisions.
 *
 * @author Brian
 * @version 30/5
 */
public class PathChecker implements RobotObserver
{
    private static PathChecker instance;
    private ArrayList<SLAMModule> sensors;
    private SurroundingData data;
    private int[] temp = new int[8];
    private int u, d, l, r;

    /**
     * Private constructor for singleton
     * Initializes sensor modules and connects to the Navigator.
     * 
     */
    private PathChecker() {
        // WR-start
        RobotSystem.getInstance().registerObserver(this);
        // WR-end
        sensors = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            sensors.add(new CameraModule(i));
            sensors.add(new LIDARModule(i * 2));
        }
        data = new SurroundingData(-1, -1, -1, -1);
        Arrays.fill(temp, -1);
    }

    /**
     * Singleton accessor for PathChecker.
     */
    public static PathChecker getInstance() {
        if (instance == null) {
            instance = new PathChecker();
        }
        return instance;
    }

    /**
     * Receives data from the Sensor Modules and uses the closer value
     * to determine the distance from an obstacle.
     * 
     * @param position identifies which sensor is sending the data
     * @param distance the distance to the closest obstacle in that area
     */
    public void updateData(int position, int distance) {
        switch (position) {
            case 1: temp[0] = distance; break;
            case 2: temp[1] = distance; break;
            case 3: temp[2] = distance; break;
            case 4: temp[3] = distance; break;
            case 5: temp[4] = distance; break;
            case 6: temp[5] = distance; break;
            case 7: temp[6] = distance; break;
            case 8: temp[7] = distance; break;
            default: System.out.println("Unknown position: " + position);
        }
        for (int i = 0; i < 8; i++) {
            if (temp[i] == -1) {
                return;
            }
        }
        if (temp[0] < temp[1]) {
            u = temp[0];
        } else {
            u = temp[1];
        }
        if (temp[2] < temp[3]) {
            d = temp[2];
        } else {
            d = temp[3];
        }
        if (temp[4] < temp[5]) {
            l = temp[4];
        } else {
            l = temp[5];
        }
        if (temp[6] < temp[7]) {
            r = temp[6];
        } else {
            r = temp[7];
        }
        this.data = new SurroundingData(u, d, l, r);
        Arrays.fill(temp, -1);
        Navigator.getInstance().receiveData(data);
    }

    /**
     * Notifies when cleaning is done and resets this class.
     */
    @Override
    public void update() {
        // WR-start
        System.out.println("update received by PathChecker");
        // WR-end
        data = new SurroundingData(-1, -1, -1, -1);
        Arrays.fill(temp, -1);
    }
}
