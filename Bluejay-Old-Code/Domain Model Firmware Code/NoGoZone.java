import java.io.Serializable;

/**
 * The data for VirtualBoundary received from the app.
 *
 * @Brian
 * @7/6
 */
public class NoGoZone implements Serializable
{
    private int x1, x2, y1, y2;
    private String label;

    /**
     * Constructor for objects of class NoGoZone
     */
    public NoGoZone(int x1, int x2, int y1, int y2) {
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
        label = "nogozone";
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }
}
