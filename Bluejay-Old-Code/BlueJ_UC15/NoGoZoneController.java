public class NoGoZoneController
{
    private NoGoZoneModel model;

    public NoGoZoneController(NoGoZoneModel model) {
        this.model = model;
    }

    public void onZoneDrawn(int x1, int y1, int x2, int y2, String label) {
        NoGoZone zone = new NoGoZone(x1, y1, x2, y2, label);
        model.addZone(zone);
    }

    public void onCleaningComplete(FirmwareOutput output, NotificationView view) {
        if (output.cleaningComplete) {
            view.showCompletion();
        }
    }
}