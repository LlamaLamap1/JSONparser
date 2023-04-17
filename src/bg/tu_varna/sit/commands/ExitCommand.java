package bg.tu_varna.sit.commands;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length > 1) {
            throw new CommandException("Unknown command");
        }
        else
            System.out.println("Exiting the program...");
    }
}
