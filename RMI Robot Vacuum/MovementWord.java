
/**
 * Represents a movement direction for the robot.
 *
 * @Brian
 * @29/5
 */
public class MovementWord {
    
    /**
     * Enum representing possible movement directions.
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction direction;
    
    /**
     * Constructs a MovementWord with the specified direction.
     *
     * @param direction the direction of movement
     */
    public MovementWord(Direction direction) {
        this.direction = direction;
    }
    
     /**
     * Returns the direction associated with this movement.
     *
     * @return the movement direction
     */
    public Direction getDirection() {
        return direction;
    }
    
    /**
     * Returns the string representation of the movement direction.
     *
     * @return the name of the direction in uppercase
     */
    @Override
    public String toString() {
        return direction.name();
    }
}
