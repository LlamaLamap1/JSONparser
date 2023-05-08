package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.JSONPathTools.GaragePathCreator;
import bg.tu_varna.sit.JSONPathTools.JSONPathCreator;
import bg.tu_varna.sit.Parser.*;


public class CreateCommand implements Command {
    @Override
    public void execute(String[] args) throws CommandException {
        try {
            if (args.length != 2) {
                throw new CommandException("Unknown command");
            } else if (!OpenCommand.isItOpen()) {
                throw new CommandException("A file has yet to be opened");
            } else if (!ValidateCommand.isItValid()){
                throw new CommandException("File contents have not been validated or are not valid");
            }
            else{
                String[] arguments=args[1].split(" ",2);
                if (arguments.length!=2)
                    throw new CommandException("The command must have a path and a string that fallows JSON structure");
                String json=arguments[1];
                String[] path=arguments[0].split("\\.");
                if (!path[0].equals("$"))
                    throw new CommandException("Path should start with $");
                if (path.length==1 && !Garage.getInstance().getCarMap().isEmpty())
                    throw new CommandException("JSON can't have multiple root elements");
                JSONPathCreator pathCreator=new GaragePathCreator();
                pathCreator.createPath(path,json);
            }
        }
        catch (JSONException | IllegalArgumentException e) {
            System.out.println(e);
        }
    }


}
