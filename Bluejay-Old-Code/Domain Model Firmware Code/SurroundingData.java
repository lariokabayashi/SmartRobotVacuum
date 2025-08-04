
/**
 * Represents distance information for obstacles surrounding the robot
 * in four directons
 *
 * @Brian
 * @30/5
 */
public class SurroundingData
{
    private int up;
    private int down;
    private int left;
    private int right;
    
    private static final int MIN_OPEN_DISTANCE = 2;

    /**
     * Constructor for class.
     * @param up the distance above the robot to an obstacle.
     * @param down the distance below the robot to an obstacle.
     * @param left the distance to the left of the robot to an obstacle.
     * @param right the distance ot the right of the robot to an obstacle.
     */
    public SurroundingData(int up, int down, int left, int right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    /**
     * Getter for the up value.
     */
    public int getUpDistance() {
        return up;
    }

    /**
     * Getter for the down value.
     */
    public int getDownDistance() {
        return down;
    }
    
    /**
     * Getter for the left value.
     */
    public int getLeftDistance() {
        return left;
    }

    /**
     * Getter for the right value.
     */
    public int getRightDistance() {
        return right;
    }
    
    /**
     * Checks if there is a gap between the robot and an obstacle
     * in a given direction.
     */
    public boolean isOpen(MovementWord.Direction dir) {
        switch (dir) {
            case UP:
                return up >= MIN_OPEN_DISTANCE;
            case DOWN:
                return down >= MIN_OPEN_DISTANCE;
            case LEFT:
                return left >= MIN_OPEN_DISTANCE;
            case RIGHT:
                return right >= MIN_OPEN_DISTANCE;
            default:
                return false;
        }
    }
    
    /**
     * Print the data as a string.
     */
    @Override
    public String toString() {
        return "UP: " + up + ", DOWN: " + down + ", LEFT: " + left + ", RIGHT: " + right;
    }
}
