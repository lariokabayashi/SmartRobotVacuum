/**
 * Stub container for a floor-plan (image, occupancy grid, etc.).
 */
import java.io.Serializable;
import java.util.Date;

public class MapData implements Serializable {
    private byte[] mapBytes;
    private Date timestamp;

    public MapData(byte[] mapData, Date timestamp) {
        this.mapBytes = mapData;
        this.timestamp = timestamp;
    }

    // Accessor methods
    public byte[] getMapBytes() { return mapBytes; }
    public Date getTimestamp() { return timestamp; }
}

