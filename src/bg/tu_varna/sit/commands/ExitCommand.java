package bg.tu_varna.sit.commands;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Unknown command");
        }
        else
            System.out.println("Exiting the program...");
    }
}
