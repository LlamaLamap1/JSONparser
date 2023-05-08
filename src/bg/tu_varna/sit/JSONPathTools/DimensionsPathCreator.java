package bg.tu_varna.sit.JSONPathTools;

//import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Dimensions;
import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.Parser.JSONParser;
import bg.tu_varna.sit.Parser.JSONValidator;
import bg.tu_varna.sit.commands.CommandException;


public class DimensionsPathCreator implements JSONPathCreator {
    @Override
    public void createPath(String[] path, String json) throws CommandException, JSONException {
        Dimensions dimensions=Garage.getInstance().getCarMap().get(path[2]).getDimensions();

        JSONParser jsonParser=new JSONParser();
        if (!json.contains(path[4])) {
            StringBuilder sb = new StringBuilder(json);
            sb.insert(1, "\"" + path[4] + "\":");
            json = sb.toString();
        }
        JSONValidator.validateObject(json);
        if (JSONValidator.isValid()) {
            if (!dimensions.getOrder().containsKey(path[4])) {

                switch (path[4]) {
                    case "length":
                        dimensions.setLength((Double) jsonParser.parseObject(json).get(path[4]));
                        break;
                    case "height":
                        dimensions.setHeight((Double) jsonParser.parseObject(json).get(path[4]));
                        break;
                    case "width":
                        dimensions.setWidth((Double) jsonParser.parseObject(json).get(path[4]));
                        break;
                    default:
                        throw new CommandException("Invalid path! Path should fallow the garage structure");
                }

            }
            else
                throw new CommandException("Invalid path! Path already exist");
        }
    }
}
