
/**
 * Write a description of class TextInput here.
 *
 * @author Lari
 * @version 01/07
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
    public TextInput(RobotStatusInterface status,Parser parser, CleaningController cleaningController)
    {
        this.status = status;
        this.parser = parser;
        this.cleaningController = cleaningController;
    }

    public void start() throws javax.jms.JMSException
    {
        System.out.println("Robot Control System");
        System.out.println("Type 'help' for commands");
        
        String command;
        do{
            command = parser.getCommand();
            switch (command.toLowerCase()) {
                case "start":
                    cleaningController.startCleaning("Standard Mode");
                    status.startCleaning();
                    System.out.println(status.getCurrentStatus());
                    break;
                    
                case "status":
                    System.out.println("Current status:");
                    System.out.println(status.getCurrentStatus());
                    break;    
                    
                case "help":
                    help();
                    break;
                    
                case "quit":
                    cleaningController.shutdown();
                    status.stopCleaning();
                    System.out.println("Exiting...");
                    break;
                    
                default:
                    System.out.println("Unknown command. Type 'help' for available commands.");
                    break;
            }
        } while(!(command.equals("End")|| command.equals("quit")));
        
        cleaningController.shutdown();

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
