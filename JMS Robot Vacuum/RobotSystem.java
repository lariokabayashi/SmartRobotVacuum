import java.util.ArrayList;
import java.util.List;
import javax.jms.*;
import serobjs.RobotStatus;

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
    private int cleaningProgress = 0;
    private boolean isCleaning = false;
    private boolean isDocked = false;
    private Thread cleaningThread;
    private Thread batteryThread;
    
    public Boolean startCleaning() {
        if (!isCleaning) {
            isCleaning = true;
            cleaningProgress = 0;
            
            // Start cleaning thread
            cleaningThread = new Thread(() -> {
                try {
                    while (Battery.getInstance().getBattery() > 0 && isCleaning && cleaningProgress < 100) {
                        Thread.sleep(1000); // Update every second
                        cleaningProgress += 10;
                        Navigator.getInstance().navigate(currentBoundary);
                        notifyObservers();
                        
                        // Check if we should return to dock (low battery)
                        if (Battery.getInstance().getBattery() < 1) {
                            stopCleaning();
                        }
                    }
                    if (cleaningProgress >= 100) {
                        finishCleaning(storedMap);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            cleaningThread.start();
            
            // Start battery discharge thread
            batteryThread = new Thread(() -> {
                try {
                    while (isCleaning) {
                        Thread.sleep(1000); // Discharge 1% every 5 seconds
                        Battery.getInstance().discharge(1);
                        notifyObservers();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            batteryThread.start();
            
            return true;
        }
        return false;
    }
    
    public Boolean stopCleaning() {
        isCleaning = false;
        if (cleaningThread != null) {
            cleaningThread.interrupt();
        }
        if (batteryThread != null) {
            batteryThread.interrupt();
        }
        Navigator.getInstance().stop();
        return true;
    }
    
    public int getCleaningProgress() {
        return cleaningProgress;
    }
    
    public RobotStatus getStatus() {
        return new RobotStatus(
            cleaningProgress, 
            Battery.getInstance().getBattery(), 
            isCleaning, 
            Battery.getInstance().getBattery() >= 100
        );
    }
    
    /** 
     * Private constructor for singleton.
     */
    public RobotSystem() {
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
     
    public Boolean startCleaning() {
        Navigator.getInstance().navigate(currentBoundary);
        return true;
    }
    
    /**
     * Pauses the cleaning process.
     
    public Boolean stopCleaning() {
        Navigator.getInstance().stop();
        return true;
    }*/
    
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
    public void updateApp(Map output, int percent) {
        AppInput.getInstance().updateMap(output, percent);
    }
    
    public void giveCurrMap() {
        Navigator.getInstance().setUpdate();
    }
    
    public void setCleaningProgress(int progress) {
        this.cleaningProgress = progress;
        notifyObservers();
    }
    
    //public void setBatteryLevel(int level) {
      //  this.batteryLevel = level;
      //  notifyObservers();
    //}
    
    public void setCleaning(boolean cleaning) {
        this.isCleaning = cleaning;
        notifyObservers();
    }
    
    //public void setDocked(boolean docked) {
      //  this.isDocked = docked;
      //  notifyObservers();
    //}
    
    /**
     * Initiates return to dock procedure
     * @return true if docking sequence started successfully, false if already docked or cleaning in progress
     */
    public boolean returnToDock() {
        if (isDocked) {
            System.out.println("Already docked");
            return false;
        }
    
        if (isCleaning) {
            System.out.println("Cannot return to dock while cleaning");
            return false;
        }

        System.out.println("Initiating return to dock procedure...");
    
        // Stop any active threads
        if (cleaningThread != null) {
            cleaningThread.interrupt();
        }
        if (batteryThread != null) {
            batteryThread.interrupt();
        }
    
        // Start docking procedure
        new Thread(() -> {
            try {
                // Simulate returning to dock
                while (Battery.getInstance().getBattery() > 0) {
                    System.out.println("Returning to dock... Battery: " + 
                        Battery.getInstance().getBattery() + "%");
                    Battery.getInstance().discharge(1);
                    Thread.sleep(1000);
                }
            
                // Reached dock
                isDocked = true;
                Battery.getInstance().onCharger();
                System.out.println("Successfully docked. Charging...");
                notifyObservers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    
        return true;
    }

    public boolean setCleaningMode(String mode) {
        // Simply accept any mode and log it
        System.out.println("[RobotSystem] Cleaning mode set to: " + mode);
        return true;
    }
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
