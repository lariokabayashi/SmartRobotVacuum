
/**
 * Stores the charge of robot's battery
 *
 * @Brian
 * @12/6
 */
public class Battery {
    private static Battery instance = null;
    private static final int FULL_BATTERY = 100; // Now using percentage
    private int charge;
    
    private Battery() {
        charge = FULL_BATTERY;
    }
    
    public static synchronized Battery getInstance() {
        if (instance == null) {
            instance = new Battery();
        }
        return instance;
    }
    
    public boolean moved() {
        if (charge > 0) {
            charge -= 1; // Decrease by 1% each move
            if (charge <= 0) {
                RobotSystem.getInstance().noBattery();
                return false;
            }
            return true;
        }
        return false;
    }

    public void onCharger() {
        System.out.println("Charging Battery");
        charge = FULL_BATTERY;
    }
    
    public int getBattery() {
        return charge;
    }
    
    // New method for gradual discharge during operation
    public void discharge(int amount) {
        charge = Math.max(0, charge - amount);
        if (charge <= 0) {
            RobotSystem.getInstance().noBattery();
        }
    }
}

