package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.jsonio.JSONHandler;


public class CloseCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Unknown command");
        } else if (!OpenCommand.isItOpen()) {
            System.out.println("File is not opened");
        } else {
            String fileName=JSONHandler.getDirectory();
            if (ValidateCommand.isItValid()){
                Garage.removeGarage();
            }
            OpenCommand.setIsOpen();

            System.out.println("Successfully closed "+fileName);
        }
    }
}
