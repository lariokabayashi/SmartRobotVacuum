import java.io.Serializable;

/**
 * Represents a command instruction for the robot to execute.
 *
 * @Brian
 * @29/5
 */
public class CommandWord implements Serializable 
{
    /**
     * Enum representing the possible types of commands.
     */
    public enum CommandType {
        START, STOP
    }

    private CommandType type;
    
    /**
     * Constructs a CommandWord with the specified command type.
     */
    public CommandWord(CommandType type) {
        this.type = type;
    }
    
    /**
     * Returns the type of the command
     */
    public CommandType getType() {
        return type;
    }
}
