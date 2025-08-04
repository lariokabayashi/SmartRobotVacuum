/**
 * VIEW-ish helper that listens to BoundaryStorage and redraws
 * overlays on the floor-plan image.
 */
import java.util.List;
import java.awt.Polygon;

public class MapHandler implements Observer {
    private Map floorMap;

    /* Concrete Subject → strongly-typed update */
    public void update(List<Polygon> zones) {
        // ... update overlay graphics ...
    }

    /* Generic Observer entry-point */
    @Override public void update(Object arg) {
        if (arg instanceof List<?>) {
            try {
                @SuppressWarnings("unchecked")
                List<Polygon> zones = (List<Polygon>) arg;
                update(zones);
            } catch (ClassCastException ignored) {}
        }
    }

    public void highlightArea(Polygon area) { /* ... */ }
    public void renderOverlay(Polygon zone) { /* ... */ }
}

