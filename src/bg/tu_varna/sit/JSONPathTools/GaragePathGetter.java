package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.Parser.JSONValidator;
import bg.tu_varna.sit.commands.CommandException;

public class GaragePathGetter implements JSONPathGetter{
    @Override
    public String getElement(String[] path) throws CommandException {
        if (!path[1].equals("garage"))
            throw new CommandException("Invalid path: Path should contain garage after the $. element");
        if (path.length == 2) {
            JSONValidator.initialize();
            if (JSONValidator.isValid()) {
                Garage garage = Garage.getInstance();
                return garage.toJSON();
            }
        } else {
            CarPathGetter carPathGetter = new CarPathGetter();
            return carPathGetter.getElement(path);
        }
        throw new CommandException("Invalid path! Path doesn't exist");
    }
}
