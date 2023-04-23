package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.jsonio.JSONHandler;


public class CloseCommand implements Command {
    @Override
    public void execute(String[] args) throws CommandException{
        if (args.length > 1) {
            throw new CommandException("Unknown command");
        } else if (!OpenCommand.isItOpen()) {
            throw new CommandException("A file has yet to be opened");
        } else {
            String fileName=JSONHandler.getDirectory();
            if (ValidateCommand.isItValid()){
                Garage.removeGarage();
            }
            OpenCommand.setIsOpen();
            ValidateCommand.setIsValid(false);

            System.out.println("Successfully closed "+fileName);
        }
    }
}
