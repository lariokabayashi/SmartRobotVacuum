import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CleaningService extends Remote {
    /**
     * Retrieves the current cleaning progress.
     * @return Progress percentage (0.0–100.0)
     */
    float getProgress() throws RemoteException;

    /**
     * Retrieves the most recent map data.
     * @return Serialized MapData object
     */
    MapData getUpdatedMap() throws RemoteException;
}

