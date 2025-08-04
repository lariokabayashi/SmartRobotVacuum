/**
 * Write a description of class Command here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Command
{
    // instance variables - replace the example below with your own
    private static final String validCommands[] = 
        {"start", "status", "quit", "help"}; //list all possible commands

    /**
     * Constructor for objects of class Command
     */
    public Command()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean isCommand(String a)    
    {
        if(a != null)
        {
            for(int i = 0;
            i < validCommands.length;
            i++) 
            {
                if(validCommands[i].equals(a)) {
                    return true;
                }
            }       
        }        
        return false;
    }    
    
    public void allOutput()     
    {
        for(String command : validCommands) 
        {
            System.out.print(command + "  ");
        }        
        System.out.println();
    }
}