import java.util.List;

/**
 * View that displays the current map and zones and sends updates to firmware.
 */
public class MapView implements ModelObserver {
    private AppToFirmwareSender appSender;
    private int[][] currentMap;

    public MapView(AppToFirmwareSender appSender, int[][] initialMap) {
        this.appSender = appSender;
        this.currentMap = initialMap;
    }
    
    public int[][] getCurrentMap() {
        return currentMap;
    }

    public void modelUpdated(List<NoGoZone> zones) {
        System.out.println("MapDisplay updated with " + zones.size() + " zone(s):");

        // Draw no-go zones on the current map
        for (NoGoZone zone : zones) {
            for (int i = zone.yStart; i <= zone.yEnd; i++) {
                for (int j = zone.xStart; j <= zone.xEnd; j++) {
                    if (i >= 0 && i < currentMap.length && j >= 0 && j < currentMap[0].length) {
                        currentMap[i][j] = 2; // mark as no-go
                    }
                }
            }
        }

        // Print map and send to firmware
        for (int i = 0; i < currentMap.length; i++) {
            for (int j = 0; j < currentMap[i].length; j++) {
                System.out.print(currentMap[i][j] + " ");
            }
            System.out.println();
        }

        appSender.sendUpdatedMap(currentMap);
    }
}