import java.util.List;
import java.util.Arrays;

/**
 * Navigation logic when no map is available.
 *
 * @Brian
 * @30/5
 */
public class BlindNavigationStrategy implements NavigationStrategy {
    private final MapGenerator mg = MapGenerator.getInstance();

    /**
     * Determines the next best move in absence of a map.
     * 
     * @param data the data of surrounding obstacles
     * @param vb rectangle set by the user for the robot to avoid.
     * @param lastMove the last move performed by the robot.
     * @return the calculated movement command.
     */
    @Override
    public MovementWord calculateNextMove(SurroundingData data, VirtualBoundary vb, MovementWord lastMove) {
        int x = mg.getX();
        int y = mg.getY();
        Map map = mg.getCurrMap();

        List<MovementWord.Direction> directions = Arrays.asList(
                MovementWord.Direction.UP,
                MovementWord.Direction.RIGHT,
                MovementWord.Direction.DOWN,
                MovementWord.Direction.LEFT
            );

        for (MovementWord.Direction dir : directions) {
            if (data.isOpen(dir)) {
                int newX = x, newY = y;
                switch (dir) {
                    case UP:
                    newY--;
                    break;
                    case DOWN:
                    newY++;
                    break;
                    case LEFT:
                    newX--;
                    break;
                    case RIGHT:
                    newX++;
                    break;
                }

                if (isWithinBounds(map, newX, newY)) {
                    int tile = map.getValue(newX, newY);
                    if (tile == 4 || tile == 2) {
                        return new MovementWord(dir);
                    }
                }
            }
        }

        for (MovementWord.Direction dir : directions) {
            if (data.isOpen(dir)) {
                int newX = x, newY = y;
                switch (dir) {
                    case UP:
                    newY--;
                    break;
                    case DOWN:
                    newY++;
                    break;
                    case LEFT:
                    newX--;
                    break;
                    case RIGHT:
                    newX++;
                    break;
                }

                if (isWithinBounds(map, newX, newY) && map.getValue(newX, newY) == 0) {
                    return new MovementWord(dir);
                }
            }
        }

        int[] start = map.getStart();
        if (!(x == start[0] && y == start[1])) {
            int dx = start[0] - x;
            int dy = start[1] - y;
            if (Math.abs(dx) >= Math.abs(dy)) {
                if (dx < 0 && data.isOpen(MovementWord.Direction.RIGHT)) {
                    return new MovementWord(MovementWord.Direction.RIGHT);
                } else if (dx > 0 && data.isOpen(MovementWord.Direction.LEFT)) {
                    return new MovementWord(MovementWord.Direction.LEFT);
                }
            } else {
                if (dy < 0 && data.isOpen(MovementWord.Direction.DOWN)) {
                    return new MovementWord(MovementWord.Direction.DOWN);
                } else if (dy > 0 && data.isOpen(MovementWord.Direction.UP)) {
                    return new MovementWord(MovementWord.Direction.UP);
                }
            }
        }

        if (lastMove != null && data.isOpen(lastMove.getDirection())) {
            return lastMove;
        }

        // WR-start
        System.out.println("calculating blind next move "
            + MovementWord.Direction.UP);
        // WR-end
        return new MovementWord(MovementWord.Direction.UP);
    }

    /**
     * Helper function for checking if a given coordinate is within bounds.
     * 
     * @param map the currently built map
     * @
     */
    private boolean isWithinBounds(Map map, int x, int y) {
        return x >= 0 && y >= 0 && x < map.width() && y < map.height();
    }
}
