
/**
 * Interface for the navigation.
 *
 * @Brian
 * @30/5
 */
public interface NavigationStrategy
{
    /**
     * Calculates next movement for the robot
     *
     * @param  sd, vb, lastMove
     * @return   MovementWord
     */
    MovementWord calculateNextMove(SurroundingData data, VirtualBoundary vb, MovementWord lastMove);
}
