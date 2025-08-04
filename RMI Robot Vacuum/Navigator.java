/**
 * Navigator controls pathfinding by interacting with PathChecker
 * and request previous maps if available from RobotSystem.
 *
 * @author Brian
 * @version 30/5
 */
public class Navigator implements RobotObserver
{
    private static Navigator instance;

    private SurroundingData currArea;
    private MovementWord nextMove;
    private boolean stop;
    private boolean begin;
    private boolean paused;
    private boolean update;
    private VirtualBoundary vb;
    private NavigationStrategy strategy;

    /**
     * Private constructor for singleton pattern.
     * Initializes internal references.
     */
    private Navigator() {
        // WR-start
        RobotSystem.getInstance().registerObserver(this);
        // WR-end
        nextMove = new MovementWord(MovementWord.Direction.UP);
        stop = false;
        begin = true;
        paused = false;
    }

    /**
     * Singleton accessor for Navigator.
     */
    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    /**
     * Receives path data from PathChecker, updates state,
     * calculates next route, and sends next movement command.
     * 
     * @param sd compiled data about nearby obstacles from sensors
     */
    public void receiveData(SurroundingData sd) {
        if (stop) {
            return;            
        }
        if (update) {
            MapGenerator.getInstance().updateApp();
            update = false;
        }
        currArea = sd;
        if (begin) {
            MapGenerator.getInstance().newMap(currArea, vb);
            begin = false;
        } else {
            if (paused) {
                MapGenerator.getInstance().continueMapping(currArea); 
                paused = false;
            } else {
                MapGenerator.getInstance().updateMap(nextMove, currArea);
            }
        }
        if (stop) {
            return;            
        }
        nextMove = calculateRoute();
        RobotSystem.getInstance().receiveMovement(nextMove);   
    }

    /**
     * Calculates a route using map data from RobotSystem.
     * Fallbacks to live PathChecker data if no usable map is found.
     * 
     * @return the next movement for the robot
     */
    private MovementWord calculateRoute() {
        if (RobotSystem.getInstance().hasMap()) {
            strategy = new MappedNavigationStrategy(MapGenerator.getInstance().getPrevMap());
        } else {
            strategy = new BlindNavigationStrategy();
        }
        return strategy.calculateNextMove(currArea, vb, nextMove);
    }

    /**
     * Starts the navigation routine.
     * 
     * @param vb the rectange marked by the user to avoid.
     */
    public void navigate(VirtualBoundary vb) {
        if (update) {
            MapGenerator.getInstance().updateApp();
            update = false;
        }
        stop = false;
        begin = true;
        paused = false;
        this.vb = vb;
        System.out.println("Cleaning  start.");
        MapGenerator.getInstance().reset();
        if (RobotSystem.getInstance().hasMap()) {
            MapGenerator.getInstance().setPrevMap(RobotSystem.getInstance().getPrevMap().copy());
        }
        PathChecker.getInstance();
        Simulation.getInstance().sendInitialData();
        // put time here maybe
    }

    /**
     * Stops the navigation routine.
     */
    public void stop() {
        stop = true;
        paused = true;
    }
    
    /**
     * Restarts the navigation routine from charging dock.
     */
    public void restart() {
        MapGenerator.getInstance().setToStart();
        stop = false;
        Simulation.getInstance().sendInitialData();
    }
    
    /**
     * Sets update to true
     */
    public void setUpdate() {
        update = true;
    }

    /**
     * Finishes the navigation routine.
     */
    @Override
    public void update() {
        // WR-start
        System.out.println("update received by Navigator");
        // WR-end
        stop = true;
    }
    
    public int getCurrPercent() {
        return MapGenerator.getInstance().getCurrPercent();
    }
}
