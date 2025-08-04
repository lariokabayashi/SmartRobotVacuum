import java.io.Serializable;

/**
 * Class VirtualBoundary stores a rectangular area on a grid and checks 
 * if a given point is within that rectangle.
 * The rectangle is defined by two x-coordinates and two y-coordinates.
 *
 * @Brian
 * @29/5
 */
public class VirtualBoundary implements Serializable {
    private int x1, x2, y1, y2;

    /**
     * Constructor for class VirtualBoundary.
     * Initializes the rectangle using two x-values and two y-values.
     * 
     * @param x1 First x-coordinate 
     * @param x2 Second x-coordinate 
     * @param y1 First y-coordinate
     * @param y2 Second y-coordinate
     */
    public VirtualBoundary(int x1, int x2, int y1, int y2) {
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        
        if (y1 > y2) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }

        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    
    /**
     * Getter for first x value.
     */
    public int getX1() {
        return x1;
    }

    /**
     * Getter for second x value.
     */
    public int getX2() {
        return x2;
    }

    /**
     * Getter for first y value.
     */
    public int getY1() {
        return y1;
    }

    /**
     * Getter for second y value.
     */
    public int getY2() {
        return y2;
    }

    /**
     * Method to check if a specific point is within 
     * the restricted rectangular area.
     *
     * @param x The x-coordinate to check
     * @param y The y-coordinate to check
     * @return true if the point (x, y) is inside the rectangle, 
     * false otherwise
     */
    public boolean isRestricted(int x, int y) {
        return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
    }

    /**
     * Method to display the boundaries of the restricted area
     */
    public void displayBoundary() {
        System.out.println("Restricted Area:");
        System.out.println("X1: " + x1 + ", X2: " + x2);
        System.out.println("Y1: " + y1 + ", Y2: " + y2);
    }
}
