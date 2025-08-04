import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

/**
 * Write a description of class CleaningServiceImpl here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CleaningServiceImpl extends UnicastRemoteObject implements CleaningService {
    private float progress = 0.0f;

    protected CleaningServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public float getCleaningProgress() throws RemoteException {
        // Simulate progress
        progress += 5.0f;
        if (progress > 100.0f) progress = 100.0f;
        return progress;
    }
}