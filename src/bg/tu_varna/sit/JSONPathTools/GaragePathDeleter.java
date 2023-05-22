package bg.tu_varna.sit.JSONPathTools;


import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.Parser.JSONValidator;
import bg.tu_varna.sit.commands.CommandException;


public class GaragePathDeleter implements JSONPathDeleter {
    @Override
    public void deletePath(String[] path) throws CommandException {
        if (!path[1].equals("garage"))
            throw new CommandException("Invalid path: Path should contain garage after the $. element");
        if (path.length==2){
            JSONValidator.initialize();
            if (JSONValidator.isValid()){
                Garage garage=Garage.getInstance();
                garage.clearGarage();
            }
        }
        else{
            CarPathDeleter carPathDeleter=new CarPathDeleter();
            carPathDeleter.deletePath(path);
        }


    }
}
