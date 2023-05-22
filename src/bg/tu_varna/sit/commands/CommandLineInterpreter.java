package bg.tu_varna.sit.commands;

import java.util.Scanner;

public class CommandLineInterpreter {
    private static CommandLineInterpreter instance=new CommandLineInterpreter();
    private CommandProcessor commandProcessor ;


    private CommandLineInterpreter() {
        this.commandProcessor = CommandProcessor.getInstance();
        commandProcessor.registerCommand("help", new HelpCommand());
        commandProcessor.registerCommand("exit", new ExitCommand());
        commandProcessor.registerCommand("open", new OpenCommand());
        commandProcessor.registerCommand("validate", new ValidateCommand());
        commandProcessor.registerCommand("close", new CloseCommand());
        commandProcessor.registerCommand("print", new PrintCommand());
        commandProcessor.registerCommand("search", new SearchCommand());
        commandProcessor.registerCommand("set", new SetCommand());
        commandProcessor.registerCommand("create", new CreateCommand());
        commandProcessor.registerCommand("delete", new DeleteCommand());
        commandProcessor.registerCommand("move", new MoveCommand());
        commandProcessor.registerCommand("save", new SaveCommand());
        commandProcessor.registerCommand("saveas", new SaveAsCommand());
    }

    public void addCommand(String name, Command command){
        commandProcessor.registerCommand(name,command);
    }

    public static CommandLineInterpreter getInstance() {
        return instance;
    }

    public void run(){
        try {
            Scanner scanner = new Scanner(System.in);
            String line;
            do {
                System.out.print("> ");
                line = scanner.nextLine();
                commandProcessor.executeCommand(line);
            } while (!line.equals("exit"));
        }
        catch (CommandException ex){
            System.out.println(ex);
        }
    }
}