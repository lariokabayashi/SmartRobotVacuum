import java.util.ArrayList;
import java.io.Serializable;

/**
 * The Map that is sent to the App
 *
 * @Brian
 * @7/6
 */
public class MapData implements Serializable
{
    private ArrayList<ArrayList<Integer>> mapData;
    private boolean cleaningComplete; 

    public MapData(ArrayList<ArrayList<Integer>> mapData, boolean cleaningComplete) {
        this.mapData = mapData;
        this.cleaningComplete = cleaningComplete;
    }

    public ArrayList<ArrayList<Integer>> getMapData() {
        return mapData;
    }

    public void setMapData(ArrayList<ArrayList<Integer>> mapData) {
        this.mapData = mapData;
    }

    public boolean isCleaningComplete() {
        return cleaningComplete;
    }

    public void setCleaningComplete(boolean cleaningComplete) {
        this.cleaningComplete = cleaningComplete;
    }
}
