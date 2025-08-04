import java.awt.Polygon;

/**
 * CONTROLLER – business rule checks for new polygons.
 */
public class BoundaryValidator {
    private final BoundaryStorage storage;
    public BoundaryValidator(BoundaryStorage s){ this.storage = s; }

    public boolean isValid(Polygon z){
        // TODO: implement real geometry / overlap test
        return true;
    }
}

