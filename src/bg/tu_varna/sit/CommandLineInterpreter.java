package bg.tu_varna.sit;

import java.util.Scanner;

public class CommandLineInterpreter {
    private static CommandLineInterpreter instance=new CommandLineInterpreter();
    private final CommandProcessor commandProcessor ;


    private CommandLineInterpreter() {
        this.commandProcessor = CommandProcessor.getInstance();
        commandProcessor.registerCommand("help", new HelpCommand());
    }

    public static CommandLineInterpreter getInstance() {
        return instance;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line;
        do {
            System.out.print("> ");
            line = scanner.nextLine();
            commandProcessor.executeCommand(line);
        } while (!line.equals("exit"));
    }
}