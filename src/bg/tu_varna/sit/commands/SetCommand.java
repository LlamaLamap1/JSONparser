package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.JSONPathTools.GaragePathSetter;
import bg.tu_varna.sit.JSONPathTools.JSONPathSetter;
import bg.tu_varna.sit.Parser.*;


public class SetCommand implements Command {
    @Override
    public void execute(String[] args) throws CommandException {
        try {
            if (args.length != 2) {
                throw new CommandException("Unknown command");
            } else if (!OpenCommand.isItOpen()) {
                throw new CommandException("File is not opened");
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
                    throw new CommandException("Path should start with $. and should have a structure like $.[element1].[element2]");
                if (path.length==1)
                    throw new CommandException("JSON can't have multiple root elements");
                JSONPathSetter pathSetter=new GaragePathSetter();
                pathSetter.setPath(path,json);
            }
        }
        catch (JSONException | IllegalArgumentException e) {
            System.out.println(e);
        }
    }


}
