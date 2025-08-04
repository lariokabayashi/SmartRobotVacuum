import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Write a description of class CleaningService here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface CleaningService extends Remote {
    float getCleaningProgress() throws RemoteException;
}
