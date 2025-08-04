
/**
 * Contains the data which the firmware receives from the app.
 *
 * @Brian
 * @29/5
 */
public class AppOutput {
    /**
     * Receives command from console and initialises it into
     * a Command Word to be processed by RobotSystem.
     * 
     * @param input The string input through the console.
     */
    public void handleCommand(String input) {
        // WR-start
        RobotSystem rs = RobotSystem.getInstance(); //clean this up later
        // WR-end
        if (input.equalsIgnoreCase("start")) {
            CommandWord command = new CommandWord(CommandWord.CommandType.START);
            rs.receiveCommand(command);
        } else if (input.equalsIgnoreCase("continue")) {
            rs.resumeCleaning();
        } else if (input.equalsIgnoreCase("stop")) {
            CommandWord command = new CommandWord(CommandWord.CommandType.STOP);
            rs.receiveCommand(command);
        } else if (input.equalsIgnoreCase("update")) {
            rs.giveCurrMap();
        } else if (input.startsWith("nogo:")) {
            try {
                String[] parts = input.substring(5).split(",");
                int x1 = Integer.parseInt(parts[0]);
                int y1 = Integer.parseInt(parts[1]);
                int x2 = Integer.parseInt(parts[2]);
                int y2 = Integer.parseInt(parts[3]);
    
                VirtualBoundary vb = new VirtualBoundary(x1, y1, x2, y2);
                rs.setNoGoZone(vb);
            } catch (Exception e) {
                System.out.println("Invalid nogo zone format. Expected: nogo:x1,y1,x2,y2");
            }
        } else if (input.equalsIgnoreCase("delete")) {
            rs.deleteMap();
        } else {
            System.out.println("Unknown command: " + input);
        }
    }
}