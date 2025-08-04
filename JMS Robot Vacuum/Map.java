import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

/**
 * Map data structure used by the vacuum robot to store the map
 * 0 = cleaned tile
 * 1 = obstacle
 * 2 = unchecked
 * 3 = virtual boundary
 * 4 = untouched
 *
 * @Brian
 * @28/5
 */
public class Map
{
    private ArrayList<ArrayList<Integer>> mapData;
    private int xStart,yStart;

    /**
     * Constructor for objects of class Map
     * Initializes the map with a given width and height.
     *
     * @param width  the width of the map
     * @param height the height of the map
     */
    public Map(int width, int height) {
        mapData = new ArrayList<>();
        this.xStart = 0;
        this.yStart = 0;

        for (int i = 0; i < height; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(4);
            }
            mapData.add(row);
        }
    }

    /**
     * Constructs an empty map.
     */
    public Map() {
        mapData = new ArrayList<>();
        ArrayList temp = new ArrayList<>();
        temp.add(4);
        mapData.add(temp);
    }

    /**
     * Set a value at a specific (x, y) coordinate.
     *
     * @param x     the x-coordinate
     * @param y     the y-coordinate
     * @param value the value to set at this coordinate
     */
    public void setValue(int x, int y, int value) {
        // WR-start
        System.out.println("x:" + x +" y:" + y + " val:"+ value);
        // WR-end
        if (isValidCoordinate(x, y)) {
            mapData.get(y).set(x, value);
        }
    }

    /**
     * Get the value at a specific (x, y) coordinate.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the value at the specified coordinate
     */
    public int getValue(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return mapData.get(y).get(x);
        }
        return -1;
    }

    /**
     * Checks if a value exists at a specific (x, y) coordinate.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if a value exists at the specified coordinates, 
     * false otherwise
     */
    public boolean exists(int x, int y) {
        return isValidCoordinate(x, y) && mapData.get(y).get(x) != 0;
    }

    /**
     * Checks if the given coordinates are within bounds of the map.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if the coordinates are valid, false otherwise
     */
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && y >= 0 && y < mapData.size() && x < mapData.get(y).size();
    }

    /**
     * Prints the entire map.
     */
    public void printMap() {
        for (ArrayList<Integer> row : mapData) {
            for (Integer value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    /**
     * Edits the map to display the virtual boundary set by the user.
     * 
     * @param vb virtual boundary set by the user.
     */
    public void applyVirtualBoundary(VirtualBoundary vb) {
        if (vb.getX1() == -1) {
            return;
        }
        int x1 = Math.min(vb.getX1(), vb.getX2());
        int x2 = Math.max(vb.getX1(), vb.getX2());
        int y1 = Math.min(vb.getY1(), vb.getY2());
        int y2 = Math.max(vb.getY1(), vb.getY2());

        for (int y = y1; y <= y2; y++) {
            if (y < 0 || y >= mapData.size()) continue;

            ArrayList<Integer> row = mapData.get(y);
            for (int x = x1; x <= x2; x++) {
                if (x >= 0 && x < row.size()) {
                    row.set(x, 3);
                }
            }
        }
    }

    /**
     * Adds a new row or column to the grid based on the given 
     * movement direction.
     * All the new cells added are set to 0.
     *
     * @param movement the MovementWord object 
     */
    public void addRowOrColumn(MovementWord movement) {
        MovementWord.Direction direction = movement.getDirection();
        System.out.println(movement.toString());
        switch (direction) {
            case UP:
            int widthTop = mapData.isEmpty() ? 0 : mapData.get(0).size();
            ArrayList<Integer> newRowTop = new ArrayList<>();
            for (int i = 0; i < widthTop; i++) {
                newRowTop.add(4);
            }
            mapData.add(0, newRowTop);
            yStart++;
            break;
            case DOWN:
            int widthBottom = mapData.isEmpty() ? 0 : mapData.get(0).size();
            ArrayList<Integer> newRowBottom = new ArrayList<>();
            for (int i = 0; i < widthBottom; i++) {
                newRowBottom.add(4);
            }
            mapData.add(newRowBottom);
            break;
            case LEFT:
            for (ArrayList<Integer> row : mapData) {
                row.add(0, 4);
            }
            xStart++;
            break;
            case RIGHT:
            for (ArrayList<Integer> row : mapData) {
                row.add(4);
            }
            break;
            default:
            System.out.println("Invalid Direction");
        }
    }

    /**
     * Gets height.
     */
    public int height() {
        return mapData.size();
    }

    /**
     * Gets the width
     */
    public int width() {
        if (mapData == null || mapData.isEmpty()) {
            return 0;
        }
        return mapData.get(0).size();
    }

    /**
     * a BFS search of the current map to see if all reachable areas are 
     * cleaned.
     */
    public boolean isCleaningDone() {
        int height = this.height();
        int width = this.width();
        boolean[][] visited = new boolean[width][height];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{xStart, yStart});
        visited[xStart][yStart] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];

            int value = this.getValue(x, y);
            if (value == 4) {
                return false;
            }

            for (int[] dir : new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (this.isValidCoordinate(nx, ny) && !visited[nx][ny]) {
                    int nextVal = this.getValue(nx, ny);
                    if (nextVal != 1 && nextVal != 3) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        
        return true;
    }

    /**
     * does a deep copy of this map
     */
    public Map copy() {
        Map copy = new Map();
        copy.mapData.clear();

        for (ArrayList<Integer> row : this.mapData) {
            ArrayList<Integer> newRow = new ArrayList<>();
            for (Integer value : row) {
                newRow.add(value);
            }
            copy.mapData.add(newRow);
        }
    
        copy.xStart = this.xStart;
        copy.yStart = this.yStart;

        return copy;
    }

    /**
     * Checks if current map is empty.
     */
    public boolean isEmpty() {
        if (mapData.size() == 1) {
            List<Integer> inner = mapData.get(0);
            if (inner != null && inner.size() == 1 && inner.get(0) == 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts the map into a string format to be able to print.
     * 
     * @return string which have the grid format.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("Map (width=").append(width())
        //.append(", height=").append(height())
        //.append(", xStart=").append(xStart)
        //.append(", yStart=").append(yStart)
        //.append("):\n");

        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (x == xStart && y == yStart) {
                    sb.append("2 ");
                } else {
                    sb.append(getValue(x, y)).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Checks if the given row and column correspond to the starting point.
     * Note: In this design, x corresponds to the column and y to the row.
     *
     * @param row the current row of the robot
     * @param col the current column of the robot
     * @return true if the given position is the starting position
     */
    public boolean isAtStart(int xCoord, int yCoord) {
        return yCoord == yStart && xCoord == xStart;
    }

    /**
     * Getter for the starting position
     * 
     * @return array of the x and y starting coordinates.
     */
    public int[] getStart() {
        return new int[] { xStart, yStart };
    }

    /**
     * Getter for the starting position
     * 
     * @param x the x coordinate to be checked
     * @param y the y coordinate to be checked
     * 
     * @return if the given positions do not have the value of 1 or 3.
     */
    public boolean isValidPosition(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            return false;
        }

        int cellValue = getValue(x, y);
        return cellValue != 1 && cellValue != 3;
    }

    /**
     * Reverts the map to an uncleaned state.
     */
    public void revertCleanedToUntouched() {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (getValue(x, y) == 0) {
                    setValue(x, y, 4);
                }
            }
        }
    }

    /**
     * Initialises the starting position and map. Also applies virtual boundary.
     * 
     * @param sd the distances of obstacles in each direction
     * @param vb the boundary made by the user
     * @param coords an array containing starting position
     */
    public void initialize(SurroundingData sd, VirtualBoundary vb, int[] coords) {
        int xCurr = coords[0];
        int yCurr = coords[1];

        for (int i = 0; i < sd.getUpDistance(); i++) {
            yCurr++;
            addRowOrColumn(new MovementWord(MovementWord.Direction.UP));
        }
        if (sd.getUpDistance() > 0) {
            setValue(xCurr, yCurr - sd.getUpDistance(), 1);
        }

        for (int i = 0; i < sd.getDownDistance(); i++) {
            addRowOrColumn(new MovementWord(MovementWord.Direction.DOWN));
        }
        if (sd.getDownDistance() > 0) {
            setValue(xCurr, yCurr + sd.getDownDistance(), 1);
        }

        for (int i = 0; i < sd.getLeftDistance(); i++) {
            xCurr++;
            addRowOrColumn(new MovementWord(MovementWord.Direction.LEFT));
        }
        if (sd.getLeftDistance() > 0) {
            setValue(xCurr - sd.getLeftDistance(), yCurr, 1);
        }

        for (int i = 0; i < sd.getRightDistance(); i++) {
            addRowOrColumn(new MovementWord(MovementWord.Direction.RIGHT));
        }
        if (sd.getRightDistance() > 0) {
            setValue(xCurr + sd.getRightDistance(), yCurr, 1);
        }

        setValue(xCurr, yCurr, 0);

        Map temp = this.copy();
        temp.applyVirtualBoundary(vb);
        if (temp.isCleaningDone() && temp.isAtStart(xCurr, yCurr)) {
            // WR replaced RobotSystem by AppInput:
            AppInput.getInstance().setOutputMap(this.copy());
            revertCleanedToUntouched();
            RobotSystem.getInstance().finishCleaning(this);
        }

        coords[0] = xCurr;
        coords[1] = yCurr;
    }

    /**
     * Updates the map and robot position based on the next movement direction and sensor data.
     * 
     * @param nextMove the direction of the next robot movement
     * @param sd the distances of obstacles in each direction
     * @param vb the boundary made by the user
     * @param coords an array containing starting position
     */
    public void update(MovementWord nextMove, SurroundingData sd, VirtualBoundary vb, int[] coords) {
        int xCurr = coords[0];
        int yCurr = coords[1];

        switch (nextMove.getDirection()) {
            case UP: {
                if (yCurr == 0) {
                    addRowOrColumn(new MovementWord(MovementWord.Direction.UP));
                    yCurr++;
                }
                yCurr--;
            }
            break;
            case DOWN: {
                if (yCurr == height() - 1) {
                    addRowOrColumn(new MovementWord(MovementWord.Direction.DOWN));
                }
                yCurr++;
            }
            break;
            case LEFT: {
                if (xCurr == 0) {
                    addRowOrColumn(new MovementWord(MovementWord.Direction.LEFT));
                    xCurr++;
                }
                xCurr--;
            }
            break;
            case RIGHT: {
                if (xCurr == width() - 1) {
                    addRowOrColumn(new MovementWord(MovementWord.Direction.RIGHT));
                }
                xCurr++;
            }
            break;
        }

        int upEdge = yCurr - sd.getUpDistance();
        while (upEdge < 0) {
            addRowOrColumn(new MovementWord(MovementWord.Direction.UP));
            yCurr++;
            upEdge++;
        }
        if (sd.getUpDistance() > 0) {
            setValue(xCurr, yCurr - sd.getUpDistance(), 1);
        }

        int downEdge = yCurr + sd.getDownDistance();
        while (downEdge >= height()) {
            addRowOrColumn(new MovementWord(MovementWord.Direction.DOWN));
        }
        if (sd.getDownDistance() > 0) {
            setValue(xCurr, yCurr + sd.getDownDistance(), 1);
        }

        int leftEdge = xCurr - sd.getLeftDistance();
        System.out.println("yCurr: " + yCurr + ", nextMove: " + nextMove.toString());
        System.out.println("xCurr: " + xCurr + ", getLeftDistance: " + sd.getLeftDistance());
        while (leftEdge < 0) {
            System.out.println("here?");
            addRowOrColumn(new MovementWord(MovementWord.Direction.LEFT));
            xCurr++;
            leftEdge++;
        }
        if (sd.getLeftDistance() > 0) {
            setValue(xCurr - sd.getLeftDistance(), yCurr, 1);
        }

        int rightEdge = xCurr + sd.getRightDistance();
        while (rightEdge >= width()) {
            addRowOrColumn(new MovementWord(MovementWord.Direction.RIGHT));
        }
        if (sd.getRightDistance() > 0) {
            setValue(xCurr + sd.getRightDistance(), yCurr, 1);
        }

        setValue(xCurr, yCurr, 0);

        Map temp = this.copy();
        temp.applyVirtualBoundary(vb);
        if (temp.isCleaningDone() && temp.isAtStart(xCurr, yCurr)) {
            // WR replaced RobotSystem by AppInput:
            AppInput.getInstance().setOutputMap(this.copy());
            revertCleanedToUntouched();
            RobotSystem.getInstance().finishCleaning(this);
        }

        coords[0] = xCurr;
        coords[1] = yCurr;
    }
    
    /** 
     * Continue cleaning of map from dock
     * 
     * @param sd the distances of obstacles in each direction
     */
    public void continueMap(SurroundingData sd) {
        int xCurr = xStart;
        int yCurr = yStart;

        int upEdge = yCurr - sd.getUpDistance();
        while (upEdge < 0) {
            addRowOrColumn(new MovementWord(MovementWord.Direction.UP));
            yCurr++;
            upEdge++;
        }
        if (sd.getUpDistance() > 0) {
            setValue(xCurr, yCurr - sd.getUpDistance(), 1);
        }

        int downEdge = yCurr + sd.getDownDistance();
        while (downEdge >= height()) {
            addRowOrColumn(new MovementWord(MovementWord.Direction.DOWN));
        }
        if (sd.getDownDistance() > 0) {
            setValue(xCurr, yCurr + sd.getDownDistance(), 1);
        }

        int leftEdge = xCurr - sd.getLeftDistance();
        while (leftEdge < 0) {
            addRowOrColumn(new MovementWord(MovementWord.Direction.LEFT));
            xCurr++;
            leftEdge++;
        }
        if (sd.getLeftDistance() > 0) {
            setValue(xCurr - sd.getLeftDistance(), yCurr, 1);
        }

        int rightEdge = xCurr + sd.getRightDistance();
        while (rightEdge >= width()) {
            addRowOrColumn(new MovementWord(MovementWord.Direction.RIGHT));
        }
        if (sd.getRightDistance() > 0) {
            setValue(xCurr + sd.getRightDistance(), yCurr, 1);
        }

        setValue(xCurr, yCurr, 0);

        Map temp = this.copy();
        if (temp.isCleaningDone() && temp.isAtStart(xCurr, yCurr)) {
            // WR replaced RobotSystem by AppInput:
            AppInput.getInstance().setOutputMap(this.copy());
            revertCleanedToUntouched();
            RobotSystem.getInstance().finishCleaning(this);
        }
    }
    
    public void updateApp() {
        Map temp = this.copy();
        int percent = temp.getCleaningProgress();
        RobotSystem.getInstance().updateApp(this, percent);
    }
    
    /**
     * Returns the cleaning progress as a percentage between 0.0 and 100.0.
     * Uses BFS
     * 
     * @return progress percentage from 0.0 to 100.0
     */
    public int getCleaningProgress() {
        int height = this.height();
        int width = this.width();
        boolean[][] visited = new boolean[width][height];
    
        int reachable = 0;
        int cleaned = 0;
    
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{xStart, yStart});
        visited[xStart][yStart] = true;
    
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
    
            int value = this.getValue(x, y);
            
            if (value == 0) {
                cleaned++;
                reachable++;
            } else if (value == 4) {
                reachable++;
            }
    
            for (int[] dir : new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}) {
                int nx = x + dir[0];
                int ny = y + dir[1];
    
                if (this.isValidCoordinate(nx, ny) && !visited[nx][ny]) {
                    int nextVal = this.getValue(nx, ny);
                    if (nextVal != 1 && nextVal != 3) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    
        if (reachable == 0) return 100;
        return (int) ((double) cleaned / reachable * 100);
    }
    
    public int getCurrPercent() {
        Map temp = this.copy();
        int percent = temp.getCleaningProgress();
        return percent;
    }
}
