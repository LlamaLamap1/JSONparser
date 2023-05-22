package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.JSONPathTools.GaragePathDeleter;
import bg.tu_varna.sit.JSONPathTools.JSONPathDeleter;



public class DeleteCommand implements Command {
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
                String[] path=args[1].split("\\.");
                if (!path[0].equals("$"))
                    throw new CommandException("Path should start with $. and should have a structure like $.[element1].[element2]");
                if (path.length==1)
                    throw new CommandException("Can't delete root element");
                JSONPathDeleter pathDeleter=new GaragePathDeleter();
                pathDeleter.deletePath(path);
            }
        }
        catch ( IllegalArgumentException e) {
            System.out.println(e);
        }
    }


}
