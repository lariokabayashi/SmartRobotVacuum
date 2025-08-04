/**
 * Represents the App's sender to firmware that sends no-go zone updates from the app.
 */
public class AppToFirmwareSender {
    public void sendUpdatedMap(int[][] updatedMap) {
        System.out.println("RobotFirmwareReceiver received updated map with zones:");
        for (int i = 0; i < updatedMap.length; i++) {
            for (int j = 0; j < updatedMap[i].length; j++) {
                System.out.print(updatedMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}