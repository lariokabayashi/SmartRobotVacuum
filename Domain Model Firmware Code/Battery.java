
/**
 * Stores the charge of robot's battery
 *
 * @Brian
 * @12/6
 */
public class Battery {
    private static Battery instance = null;
    private static final int FULL_BATTERY = 10;
    private int charge;
    
    // Private constructor
    private Battery() {
        charge = FULL_BATTERY;
    }
    
    // Public method to provide access
    public static synchronized Battery getInstance() {
        if (instance == null) {
            instance = new Battery();
        }
        return instance;
    }
    
    public boolean moved() {
        charge--;
        if (charge <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void onCharger() {
        charge = FULL_BATTERY;
    }
}

