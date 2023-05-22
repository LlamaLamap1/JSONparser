package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.JSONPathTools.GaragePathMover;
import bg.tu_varna.sit.JSONPathTools.JSONPathMover;
import bg.tu_varna.sit.Parser.JSONException;


public class MoveCommand implements Command {
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
                    throw new CommandException("The command must have a two paths");

                String[] from=arguments[0].split("\\.");
                String[] to=arguments[1].split("\\.");
                if (!from[0].equals("$") || !to[0].equals("$"))
                    throw new CommandException("Path should start with $. and should have a structure like $.[element1].[element2]");
                if (from.length==1 || to.length==1)
                    throw new CommandException("JSON can't have multiple root elements");
                if (from.length!=to.length+1)
                    throw new CommandException("\"From\" path needs to be of one level deeper than path \"to\"");
                JSONPathMover pathMover=new GaragePathMover();
                pathMover.moveElement(from,to);
            }
        }
        catch (IllegalArgumentException | JSONException e) {
            System.out.println(e);
        }
    }


}
