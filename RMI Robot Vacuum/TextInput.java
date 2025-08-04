
/**
 * Write a description of class TextInput here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.rmi.RemoteException;

public class TextInput
{
    // instance variables 
    private RobotStatusInterface status;
    private Parser parser;
    private CleaningController cleaningController;

    /**
     * Constructor for objects of class TextInput
     */
    public TextInput(RobotStatusInterface status, Parser parser, CleaningController cleaningController)
    {
        this.status = status;
        this.parser = parser;
        this.cleaningController = cleaningController;
    }

    public void start() throws javax.jms.JMSException{
        
        System.out.println("Robot Control System");
        System.out.println("Type 'help' for commands");
        
        String command;
        do{
            command = parser.getCommand();
            switch (command.toLowerCase()) {
                case "start":
                    cleaningController.startCleaning();
                    
                     try {
                        status.startCleaning();
                        System.out.println("Progress: " + status.getCleaningProgress() + "%");
                        System.out.println("Battery: " + status.getBatteryLevel() + "%");
                    } catch (RemoteException e) {
                        System.err.println("Error starting: " + e.getMessage());
                    }
                    break;
                case "status":
                    try {
                        System.out.println("Current status:");
                        System.out.println("- Progress: " + status.getCleaningProgress() + "%");
                        System.out.println("- Battery: " + status.getBatteryLevel() + "%");
                    } catch (RemoteException e) {
                        System.err.println("Error getting status: " + e.getMessage());
                    }
                    break;
                case "help":
                    help();
                    break;
                    
                case "quit":
                    try{
                    status.stopCleaning();
                    System.out.println("Exiting...");
                    } catch (RemoteException e) {
                        System.err.println("Error stopping: " + e.getMessage());
                    }
                    break;
                    
                default:
                    System.out.println("Unknown command. Type 'help' for available commands.");
                    break;
            }
        } while(!(command.equals("End")|| command.equals("quit")));

        System.out.println("Goodbye");
    }

    private void help()
    {
        parser.showCommands();
        System.out.println("Available commands:");
        System.out.println("start    - Begin cleaning");
        System.out.println("status   - Show status");
        System.out.println("help     - Show this help");
        System.out.println("quit     - Exit the program");
    }
}
