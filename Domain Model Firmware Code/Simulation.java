import java.util.Random;

/**
 * Simulation class to simulate a robot's sensor data and movement 
 * in a manually defined grid.
 *
 * @Brian
 * @31/5
 */
public class Simulation {
    private static Simulation instance;

    private int[][] grid;
    private int robotRow;
    private int robotCol;
    private int rows;
    private int cols;

    // WR-start
    private int count;
    // WR-end

    private final int[][] sensorDirections = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
        };
        
    String[] gridRows = {
                "11111111",
                "10200001",
                "10000001",
                "11111111"
            };

    /**
     * Private constructor sets up the simulation using a manually defined grid.
     * The grid is defined via a string array for clear visual layout.
     *
     */
    private Simulation() {
        // WR-start
        this.count= 0;
        // WR-end

        rows = gridRows.length;
        cols = gridRows[0].length();
        grid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            String rowStr = gridRows[i];
            for (int j = 0; j < cols; j++) {
                grid[i][j] = rowStr.charAt(j) - '0';
                if (grid[i][j] == 2) {
                    robotRow = i;
                    robotCol = j;
                }
            }
        }
    }

    /**
     * Returns the singleton instance of the Simulation class.
     *
     * @return the single Simulation instance.
     */
    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    /**
     * Scans from the robot's current position in the given direction
     * for up to 10 cells. Returns the distance to the first encountered
     * obstacle. If no obstacle is found or if the ray goes out of bounds,
     * returns 0.
     *
     * @param dRow row increment
     * @param dCol column increment
     * @return the distance to the obstacle or 0 if none is detected.
     */
    private int scanDirection(int dRow, int dCol) {
        int r = robotRow;
        int c = robotCol;
        for (int step = 1; step <= 10; step++) {
            r += dRow;
            c += dCol;
            if (!inBounds(r, c)) {
                return 0;
            }
            if (grid[r][c] == 1) {
                return step;
            }
        }
        return 0;
    }

    /**
     * Returns sensor data for the 4 cardinal directions from the robot's cell.
     * The returned array is ordered as follows:
     *   index 0 = North, index 1 = East, index 2 = South, index 3 = West.
     *
     * @return an int array containing 4 sensor readings.
     */
    private int[] getSensorData() {
        int[] sensors = new int[4];
        for (int i = 0; i < sensorDirections.length; i++) {
            int dRow = sensorDirections[i][0];
            int dCol = sensorDirections[i][1];
            sensors[i] = scanDirection(dRow, dCol);
        }
        return sensors;
    }

    /**
     * Sends the current sensor data to the PathChecker.
     * Since PathChecker expects 8 sensor readings,
     * the same value is sent twice for each direction:
     *   - North -> positions 1 and 2
     *   - South -> positions 3 and 4
     *   - West  -> positions 5 and 6
     *   - East  -> positions 7 and 8
     */
    private void sendSensorData() {
        Random rand = new Random(System.currentTimeMillis());       
        int[] sensorData = getSensorData();

        int randomExtra = rand.nextInt(10) + 1;
        if (rand.nextBoolean()) {
            PathChecker.getInstance().updateData(1, sensorData[0] + randomExtra);
            PathChecker.getInstance().updateData(2, sensorData[0]);
        } else {
            PathChecker.getInstance().updateData(1, sensorData[0]);
            PathChecker.getInstance().updateData(2, sensorData[0] + randomExtra);
        }

        randomExtra = rand.nextInt(10) + 1;
        if (rand.nextBoolean()) {
            PathChecker.getInstance().updateData(3, sensorData[2] + randomExtra);
            PathChecker.getInstance().updateData(4, sensorData[2]);
        } else {
            PathChecker.getInstance().updateData(3, sensorData[2]);
            PathChecker.getInstance().updateData(4, sensorData[2] + randomExtra);
        }

        randomExtra = rand.nextInt(10) + 1;
        if (rand.nextBoolean()) {
            PathChecker.getInstance().updateData(5, sensorData[3] + randomExtra);
            PathChecker.getInstance().updateData(6, sensorData[3]);
        } else {
            PathChecker.getInstance().updateData(5, sensorData[3]);
            PathChecker.getInstance().updateData(6, sensorData[3] + randomExtra);
        }

        randomExtra = rand.nextInt(10) + 1;
        if (rand.nextBoolean()) {
            PathChecker.getInstance().updateData(7, sensorData[1] + randomExtra);
            PathChecker.getInstance().updateData(8, sensorData[1]);
        } else {
            PathChecker.getInstance().updateData(7, sensorData[1]);
            PathChecker.getInstance().updateData(8, sensorData[1] + randomExtra);
        }
        //this.printGrid();
    }

    /**
     * Sends the initial sensor data from the simulation.
     * This method can be called to give more sensor readings.
     */
    public void sendInitialData() {
        // WR-start
        System.out.println("initial data sending");
        // WR-end
        sendSensorData();
    }

    /**
     * Processes a movement command. The robot will attempt to move one cell
     * in the specified direction. If the destination
     * cell is within bounds and is free, the move is performed.
     * After moving, the updated sensor data is sent out.
     *
     * @param movement The movement command, provided as a MovementWord object.
     */
    public void processMovement(MovementWord movement) {
        // WR-start
        System.out.println("processing move "+ ++count);
        System.out.println("robotrow: " + robotRow + ", robotcol: " + robotCol);
        // WR-end
        updateRobotPosition(movement);
        sendSensorData();
    }

    /**
     * Updates the robot's position on the grid based on the given movement direction.
     * 
     * @param movement the movement command
     */
    private void updateRobotPosition(MovementWord movement) {
        MovementWord.Direction dir = movement.getDirection();
        int newRow = robotRow;
        int newCol = robotCol;

        switch (dir) {
            case UP:
            newRow = robotRow - 1;
            break;
            case DOWN:
            newRow = robotRow + 1;
            break;
            case LEFT:
            newCol = robotCol - 1;
            break;
            case RIGHT:
            newCol = robotCol + 1;
            break;
        }

        if (inBounds(newRow, newCol) && grid[newRow][newCol] == 0) {
            grid[robotRow][robotCol] = 0;
            robotRow = newRow;
            robotCol = newCol;
            grid[robotRow][robotCol] = 2;
        } else {
            System.out.println("Invalid move: cannot move " + dir);
        }
    }
    
    public void restartCleaning() {
        for (int i = 0; i < rows; i++) {
            String rowStr = gridRows[i];
            for (int j = 0; j < cols; j++) {
                grid[i][j] = rowStr.charAt(j) - '0';
                if (grid[i][j] == 2) {
                    robotRow = i;
                    robotCol = j;
                }
            }
        }
    }

    /**
     * for bugfixing
    public void printGrid() {
    for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
    System.out.print(grid[i][j]);
    }
    System.out.println();
    }
    }
     */
}