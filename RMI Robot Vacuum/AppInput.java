import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the data which the firmware gives to the app
 *
 * @Brian
 * @29/5
 */
public class AppInput implements RobotObserver
{
    private static AppInput instance;
    private Map outputMap;

    private AppInput()
    {
        // WR-start
        RobotSystem.getInstance().registerObserver(this);
        // WR-end
    }

    /**
     * Returns the singleton instance of AppInput.
     * Uses double-checked locking for thread-safe lazy initialization.
     *
     * @return the single AppInput instance
     */
    public static AppInput getInstance()
    {
        if (instance == null) {
            synchronized (AppInput.class) {
                if (instance == null) {
                    instance = new AppInput();
                }
            }
        }
        return instance;
    }  
    

    // WR-start
    /**
     * Saves a new output map to store
     * 
     * @param outputMap a map that is more suited for user use.
     */
    public void setOutputMap(Map outputMap) {
        this.outputMap = outputMap;
    }
    // WR-end
    
    /** 
     * Notifies user that robot is out of battery
     */
    public void noBattery() {
        System.out.println("Robot is out of battery");
        
        // Also notify remote observers noBattery if needed
        /*
        for (AppRemoteObserver obs : new ArrayList<>(remoteObservers)) {
            try {
                obs.noBattery();
            } catch (RemoteException e) {
                System.err.println("Failed to notify remote observer noBattery: " + e.getMessage());
                remoteObservers.remove(obs);
            }
        }
        */
    }
    
    /**
     * Updates app on map and cleaned percentage
     */
    public void updateMap(Map output, float percent) {
        this.outputMap = output;
        System.out.println(output);
        System.out.println("Completed percent: " + percent + "%");
        //notifyRemoteObservers(output, percent);
    }

    /**
     * Notifies when map is done and prints out the map
     * 0 = Cleaned Tile
     * 1 = Obstacle
     * 2 = Starting Tile
     * 4 = Unvisited Tile
     */
    @Override
    public void update() {
        // WR-start
        System.out.println("update received by AppInput");
        System.out.println(outputMap);
        // WR-end
        
        // WR removed:
        // System.out.println(RobotSystem.getInstance().getOutputMap().toString());
    }
}
