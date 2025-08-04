
/**
 * Write a description of class MapInteractionHandler here.
 *
 * @author Larisssa
 * @version 31/05
 */
public class MapInteractionHandler {
    private NoGoZoneController controller;

    public MapInteractionHandler(NoGoZoneController controller) {
        this.controller = controller;
    }

    public void handleZoneDraw(int x1, int y1, int x2, int y2, String label) {
        controller.onZoneDrawn(x1, y1, x2, y2, label);
    }
}
