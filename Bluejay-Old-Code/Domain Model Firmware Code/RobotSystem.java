import java.util.ArrayList;
import java.util.List;

/**
 * RobotSystem is the main controller of the robot firmware and 
 * coordinates the cleaning schedule and robot actions, working 
 * with CleanScheduler, Navigator, and MapGenerator.
 *
 * @author Brian
 * @version 29/5
 */
public class RobotSystem {
    private static RobotSystem instance;

    private Map storedMap;
    private VirtualBoundary currentBoundary;
    private final List<RobotObserver> observers = new ArrayList<>();

    /** 
     * Private constructor for singleton.
     */
    private RobotSystem() {
        storedMap = new Map();
        currentBoundary = new VirtualBoundary(-1, -1, -1, -1);
    }
    
    /**
     * Gives access to the RobotSystem.
     * 
     * @return reference to RobotSystem.
     */
    public static RobotSystem getInstance() {
        if (instance == null) {
            instance = new RobotSystem();
        }
        return instance;
    }

    /**
     * Processes a CommandWord object.
     * 
     * @param command the command from the console
     */
    public void receiveCommand(CommandWord command) {
        switch (command.getType()) {
            case START:
                startCleaning();
                break;
            case STOP:
                stopCleaning();
                break;
        }
    }
    
    /** 
     * Begins the cleaning process, initialising other classes
     * if first clean.
     */
    private void startCleaning() {
        Navigator.getInstance().navigate(currentBoundary);
    }
    
    /**
     * Pauses the cleaning process.
     */
    private void stopCleaning() {
        Navigator.getInstance().stop();
    }
    
    /**
     * Receives the next move for the robot from the navigator.
     * 
     * @param mw the movement of up, down, left or right.
     */
    public void receiveMovement(MovementWord mw) {
        if (mw == null) {
            return;
        }
        if (Battery.getInstance().moved()) {
            Simulation.getInstance().processMovement(mw); //simulates moving
        } else {
            RobotSystem.getInstance().noBattery();
        }
    }
    
    /**
     * Receives the completed map of the area after a clean.
     * 
     * @param newMap the new completed map.
     */
    public void finishCleaning(Map newMap) {
        storedMap = newMap;
        notifyObservers(); //finish nav, receivemap appinput, mapgenerator can reset, pathchecker can reset
    }
    
    /**
     * Checks if a previous map exists.
     */
    public boolean hasMap() {
        if (storedMap.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    /** 
     * Getter for the map
     */
    public Map getPrevMap() {
        return storedMap;
    }
    
    /**
     * Deletes the currently stored map.
     */
    public void deleteMap() {
        storedMap = new Map();
    }
    
    /**
     * Sets the current virtual boundary.
     * 
     * @param vb the newly set virtual boundary
     */
    public void setNoGoZone(VirtualBoundary vb) {
        currentBoundary = vb;
    }
    
    /**
     * Add observer for the pattern
     */
    public void registerObserver(RobotObserver observer) {
        observers.add(observer);
    }
    
    /**
     * Remove observer
     */
    public void unregisterObserver(RobotObserver observer) {
        observers.remove(observer);
    }
    
    /**
     * Notify the observers in the pattern
     */
    private void notifyObservers() {
        for (RobotObserver observer : observers) {
            observer.update();
        }
    }
    
    /**
     * Stop movement due to no battery
     */
    public void noBattery() {
        Navigator.getInstance().stop();
        AppInput.getInstance().noBattery();
    }
    
    /**
     * Stop movement due to no battery
     */
    public void resumeCleaning() {
        Battery.getInstance().onCharger();
        Simulation.getInstance().restartCleaning();
        Navigator.getInstance().restart();
    }
    
    /**
     * Updates app on current map and percentage
     */
    public void updateApp(Map output, float percent) {
        AppInput.getInstance().updateMap(output, percent);
    }
    
    public void giveCurrMap() {
        Navigator.getInstance().setUpdate();
    }
    
    // WR removed:
    
    // /**
     // * Saves a new output map to store
     // * 
     // * @param outputMap a map that is more suited for user use.
     // */
    // public void setOutputMap(Map outputMap) {
        // this.outputMap = outputMap;
    // }
    
    // /**
     // * Getter for new output map
     // * 
     // * @return Map which matches app requirements.
     // */
    // public Map getOutputMap() {
        // return outputMap;
    // }
}