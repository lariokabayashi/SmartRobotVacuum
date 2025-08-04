/**
 * Simulated firmware output. Sends a map with values:
 * 0 = clean, 1 = mess, 2 = no-go zone.
 */
public class FirmwareOutput {
    public boolean cleaningComplete;
    public int[][] mapGrid;

    public FirmwareOutput(int[][] mapGrid, boolean cleaningComplete) {
        this.mapGrid = mapGrid;
        this.cleaningComplete = cleaningComplete;
    }

    public int[][] getMapGrid() {
        return mapGrid;
    }
}