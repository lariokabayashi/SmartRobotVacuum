/**
 * MapGenerator builds a map based on incoming movement commands
 * and sends the completed map to RobotSystem when done.
 * 
 * @author Brian
 * @version 30/5
 */
public class MapGenerator implements RobotObserver
{
    private static MapGenerator instance;

    private Map currMap;
    private int xCurr, yCurr;
    private VirtualBoundary vb;
    private Map prevMap = null;
    /**
     * Private constructor for singleton.
     */
    private MapGenerator() {
        // WR-start
        RobotSystem.getInstance().registerObserver(this);
        // WR-end
        currMap = new Map();
        xCurr = 0;
        yCurr = 0;
        vb = new VirtualBoundary(-1, -1, -1, -1);
    }

    /**
     * Singleton accessor for MapGenerator.
     * 
     * @return reference to Map Generator.
     */
    public static MapGenerator getInstance() {
        if (instance == null) {
            instance = new MapGenerator();
        }
        return instance;
    }

    /**
     * Updates the current map based on the robot's movement and sensors.
     * 
     * @param nextMove the next movement command.
     * @param sd the data of the surrounding area from the sensors.
     */
    public void updateMap(MovementWord nextMove, SurroundingData sd) {
        int[] coords = new int[]{xCurr, yCurr};
        currMap.update(nextMove, sd, vb, coords);
        if (prevMap != null) {
            int[] prevCoords = new int[]{xCurr, yCurr};
            prevMap.update(nextMove, sd, vb, prevCoords);
        }

        xCurr = coords[0];
        yCurr = coords[1];
    }

    /**
     * Creates a new map to build upon. Matches the width and height to
     * first sensor data given.
     * 
     * @param sd the data of the surrounding area from the sensors.
     * @param vb the virtual boundary set by the user.
     */
    public void newMap(SurroundingData sd, VirtualBoundary vb) {
        currMap = new Map();
        this.vb = vb;
        int[] coords = new int[]{xCurr, yCurr};
        currMap.initialize(sd, vb, coords);
        xCurr = coords[0];
        yCurr = coords[1];
    }

    /**
     * Getter for the current x value on map.
     */
    public int getX() {
        return xCurr;
    }

    /** 
     * Getter for the current y value on a map.
     */
    public int getY() {
        return yCurr;
    }

    /**
     * Getter for the current map being developed.
     */
    public Map getCurrMap() {
        return currMap;
    }

    /**
     * Resets the values of this static class to 0.
     */
    public void reset() {
        currMap = new Map();
        xCurr = 0;
        yCurr = 0;
        vb = new VirtualBoundary(-1, -1, -1, -1);
        prevMap = null;
    }

    /**
     * Sets the map from a previous clean.
     * 
     * @param map map from a previous clean
     */
    public void setPrevMap(Map map) {
        this.prevMap = map;
        int[] start = prevMap.getStart();
        prevMap.setValue(start[0], start[1], 0);
    }

    /**
     * Getter for prevMap
     * 
     * @return prevMap
     */
    public Map getPrevMap() {
        return prevMap;
    }
    
    /**
     * Resets map position to start
     */
    public void setToStart() {
        int[] start = currMap.getStart();
        xCurr = start[0];
        yCurr = start[1];
    }
    
    /**
     * Continues mapping from docking
     */
    public void continueMapping(SurroundingData sd) {
        currMap.continueMap(sd);
    }
    
    /**
     * Sends current map to app
     */
    public void updateApp() {
        currMap.updateApp();
    }
    
    /**
     * Notifies when cleaning is done and resets this class.
     */
    @Override
    public void update() {
        // WR-start
        System.out.println("update received by MapGenerator");
        // WR-end
        this.reset();
    }
}
