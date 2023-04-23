package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.Garage;


public class PrintCommand implements Command {
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("Unknown command");
        } else if (!OpenCommand.isItOpen()) {
            throw new CommandException("A file has yet to be opened");
        } else if (!ValidateCommand.isItValid()){
            throw new CommandException("File contents have not been validated or are not valid");
        }
        else{
            System.out.println(Garage.getInstance().toJSON());
        }
    }
}
