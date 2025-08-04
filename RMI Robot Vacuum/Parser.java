import java.util.Scanner;

/**
 * Write a description of class Parser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Parser
{
    // instance variables - replace the example below with your own
    private Command command;
    private Scanner scanner;

    /**
     * Constructor for objects of class Parser
     */
    public Parser()
    {
        command = new Command();
        scanner = new Scanner(System.in);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getCommand()     
    {  
        String commandWord = null;
        do { 
            // Display input prompt
            System.out.print("> ");
            String word = scanner.next();
            // Discard rest
            readLine();
            if(command.isCommand(word)) {
                commandWord = word;
            }   
            else {
                System.out.println("Unknown command: " + word);
                System.out.print("Valid commands are: ");
                command.allOutput();
            }   
        } while(commandWord == null);
        return commandWord;
    }  
    
    public void showCommands()    
    {       
        command.allOutput();
    }
    
    public String readLine()    
    {      
        return scanner.nextLine();
    }
}
