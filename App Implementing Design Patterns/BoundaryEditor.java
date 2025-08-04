/**
 * VIEW – interactive polygon editor for no-go zones.
 */
import java.util.List;
import java.awt.Polygon;
import java.awt.Point;

public class BoundaryEditor {
    public void defineZone(List<Point> points) { /* build polygon */ }
    public void sendZone(Polygon zone)         { /* -> controller */ }
    public void display() { /* UI */ }
}

