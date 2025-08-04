import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Implementation of the Robot interface.
 * 
 * @Brian
 * @8/6
 */
public class RobotImpl extends UnicastRemoteObject implements Robot {
    private MapOutput map;
    private NoGoZone noGoZone;
    private AppOutput appOutput;

    public RobotImpl() throws RemoteException {
        super();
        this.map = null;
        this.noGoZone = null;
        this.appOutput = new AppOutput();
    }

    @Override
    public void receiveNoGoZone(NoGoZone noGoZone) throws RemoteException {
        this.noGoZone = noGoZone;
        System.out.println("Received NoGoZone: " + noGoZone.toString());
        appOutput.handleCommand("nogo:" + noGoZone.getX1() + "," + noGoZone.getY1() + "," +
                noGoZone.getX2() + "," + noGoZone.getY2());
    }

    @Override
    public void receiveCommand(CommandWord command) throws RemoteException {
        System.out.println("Received command: " + command.getType());
        String input = command.getType() == CommandWord.CommandType.START ? "start" : "stop";
        appOutput.handleCommand(input);
    }
    
    //need to complete and also call from robotsystem when clean is done.
    @Override
    public MapOutput getMapOutput() throws RemoteException {
        return map;
    }
}