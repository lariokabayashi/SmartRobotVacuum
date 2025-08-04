import java.util.*;

/**
 * Strategy using a stored map with support for virtual boundaries and dynamic redirection.
 */
public class MappedNavigationStrategy implements NavigationStrategy {

    private final Map guideMap;
    private int x = 0;
    private int y = 0;

    /**
     * Constructor for this strategy
     * 
     * @param inputMap the map for the strategy to utilise.
     */
    public MappedNavigationStrategy(Map inputMap) {
        this.guideMap = inputMap.copy();
        x = MapGenerator.getInstance().getX();
        y = MapGenerator.getInstance().getY();
    }

    /**
     * Determines the next best movement for the robot using the map and bfs.
     * 
     * @param data the data of surrounding obstacles
     * @param vb rectangle set by user for the robot to avoid.
     * @param lastMove the previous move done by the robot.
     */
    @Override
    public MovementWord calculateNextMove(SurroundingData data, VirtualBoundary vb, MovementWord lastMove) {
        x = MapGenerator.getInstance().getX();
        y = MapGenerator.getInstance().getY();

        guideMap.applyVirtualBoundary(vb);
        List<MovementWord.Direction> path = bfsToNearestDirty(x, y, data);

        if (path == null || path.isEmpty()) {
            int[] start = guideMap.getStart();
            List<MovementWord.Direction> returnPath = bfsToTarget(x, y, start[0], start[1]);
            if (returnPath != null && !returnPath.isEmpty()) {
                return new MovementWord(returnPath.get(0));
            }
            return null;
        }

        // WR-start
        System.out.println("calculating map-guided next move "
            + path.get(0));
        // WR-end
        return new MovementWord(path.get(0));
    }

    /**
     * The implementation of the bts to find the next best move. It priorities finding
     * values of 4 in the grid and avoiding places the robot should not access like 1 and 3.
     * It additionaly checks with SurroundingData to block off directions which are open
     * on the map but aren't in the current scenario.
     * 
     * @param startX the starting x coordinate
     * @param startY the starting y coordinate
     * @param data the compiled sensor data which shows how close obstacles are.
     * 
     * @return a list of next optimal moves.
     */
    private List<MovementWord.Direction> bfsToNearestDirty(int startX, int startY, SurroundingData data) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Node(startX, startY, new ArrayList<>()));
        visited.add(startX + "," + startY);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int currentVal = guideMap.getValue(node.x, node.y);
            if (currentVal == 4 || currentVal == 2) {
                return node.path;
            }

            for (MovementWord.Direction dir : MovementWord.Direction.values()) {
                int nx = node.x, ny = node.y;
                switch (dir) {
                    case UP:
                    ny--;
                    break;
                    case DOWN:
                    ny++;
                    break;
                    case LEFT:
                    nx--;
                    break;
                    case RIGHT:
                    nx++;
                    break;
                }

                String key = nx + "," + ny;
                if (node.path.isEmpty() && !data.isOpen(dir)) {
                    continue;
                }

                if (!visited.contains(key) && guideMap.isValidPosition(nx, ny)) {
                    int val = guideMap.getValue(nx, ny);
                    if (val == 1 || val == 3) continue;

                    List<MovementWord.Direction> newPath = new ArrayList<>(node.path);
                    newPath.add(dir);
                    queue.offer(new Node(nx, ny, newPath));
                    visited.add(key);
                }
            }
        }

        return null;
    }

    /**
     * BFS search from the current position to a specified target position.
     * 
     * @param startX starting x coordinate
     * @param startY starting y coordinate
     * @param targetX the x coordinate to reach
     * @param targetY the y coordinate to reach
     * 
     * @param the list of the next optimal moves
     */
    private List<MovementWord.Direction> bfsToTarget(int startX, int startY, int targetX, int targetY) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Node(startX, startY, new ArrayList<>()));
        visited.add(startX + "," + startY);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x == targetX && node.y == targetY) {
                return node.path;
            }

            for (MovementWord.Direction dir : MovementWord.Direction.values()) {
                int nx = node.x, ny = node.y;
                switch (dir) {
                    case UP:
                    ny--;
                    break;
                    case DOWN:
                    ny++;
                    break;
                    case LEFT:
                    nx--;
                    break;
                    case RIGHT:
                    nx++;
                    break;
                }

                String key = nx + "," + ny;

                if (!visited.contains(key) && guideMap.isValidPosition(nx, ny)) {
                    int val = guideMap.getValue(nx, ny);
                    if (val == 1 || val == 3) continue;

                    List<MovementWord.Direction> newPath = new ArrayList<>(node.path);
                    newPath.add(dir);
                    queue.offer(new Node(nx, ny, newPath));
                    visited.add(key);
                }
            }
        }

        return null;
    }

    /**
     * Helper class to track BFS path nodes.
     */
    private static class Node {
        int x, y;
        List<MovementWord.Direction> path;

        Node(int x, int y, List<MovementWord.Direction> path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }
    }
}