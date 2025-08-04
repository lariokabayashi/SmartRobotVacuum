import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Firmware interface for the C-S Contract with the app.
 *
 * @Brian
 * @8/6
 */
public interface Robot extends Remote
{
    /**
     * Receives a NoGoZone object from the app.
     *
     * @param noGoZone the no-go zone received from the app
     * @throws RemoteException if there's an RMI issue
     */
    void receiveNoGoZone(NoGoZone noGoZone) throws RemoteException;

    /**
     * Receives a CommandWord object.
     *
     * @param command the command received from the app
     * @throws RemoteException if there's an RMI issue
     */
    void receiveCommand(CommandWord command) throws RemoteException;

    /**
     * Returns the MapOutput containing map data and cleaning status.
     *
     * @return MapOutput object with the map data and cleaning status
     * @throws RemoteException if there's an RMI issue
     */
    MapOutput getMapOutput() throws RemoteException;
}
