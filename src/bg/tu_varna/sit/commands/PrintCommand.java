package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.Garage;


public class PrintCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Unknown command");
        } else if (!OpenCommand.isItOpen()) {
            System.out.println("File is not opened");
        } else if (!ValidateCommand.isItValid()){
            System.out.println("File contents have not been validated or are not valid");
        }
        else{
            System.out.println(Garage.getInstance().toJSON());
        }
    }
}
