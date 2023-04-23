package bg.tu_varna.sit.JSONPathTools;


import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.Parser.JSONValidator;
import bg.tu_varna.sit.Parser.JSONtoGarage;
import bg.tu_varna.sit.commands.CommandException;


public class GaragePathSetter implements JSONPathSetter {
    @Override
    public void setPath(String[] path, String json) throws CommandException, JSONException {
        if (!path[1].equals("garage"))
            throw new CommandException("Invalid path: Path should contain garage after the $. element");
        if (path.length==2){
            JSONValidator.initialize();
            JSONValidator.validateObject(json);
            if (JSONValidator.isValid()){
                JSONtoGarage jsoNtoGarage=new JSONtoGarage();
                Garage.removeGarage();
                if (json.contains("garage"))
                    jsoNtoGarage.parseJSON(json);
                else
                    jsoNtoGarage.parseJSON("{\"garage\":"+json+"}");
            }
        }
        else{
            CarPathSetter carPathSetter=new CarPathSetter();
            carPathSetter.setPath(path,json);
        }


    }
}
