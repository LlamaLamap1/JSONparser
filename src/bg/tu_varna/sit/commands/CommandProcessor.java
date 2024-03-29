package bg.tu_varna.sit.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private static CommandProcessor instance=new CommandProcessor();
    private Map<String, Command> commands;

    private CommandProcessor(){
        commands = new HashMap<>();
    }

    public static CommandProcessor getInstance(){
        return instance;
    }

    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    public void executeCommand(String name) throws CommandException {
        try {
            String[] line=name.split(" ",2);
            Command command = commands.get(line[0]);
            if (command == null) {
                throw new CommandException("Unknown command");
            } else {
                command.execute(line);
            }
        }
        catch (CommandException ex){
            System.out.println(ex);
        }
    }
}
