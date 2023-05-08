package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Dimensions;
import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.Parser.JSONParser;
import bg.tu_varna.sit.Parser.JSONValidator;
import bg.tu_varna.sit.commands.CommandException;

import java.util.Map;

public class DimensionsPathSetter implements JSONPathSetter {
    @Override
    public void setPath(String[] path, String json) throws CommandException, JSONException {
        Garage garage=Garage.getInstance();
        if (garage.getCarMap().containsKey(path[2])){
            Car car=garage.getCarMap().get(path[2]);
            JSONParser jsonParser=new JSONParser();
            if (car.getOrder().containsKey(path[3])){
                if (!json.contains(path[4])) {
                    StringBuilder sb = new StringBuilder(json);
                    sb.insert(1, "\"" + path[4] + "\":");
                    json = sb.toString();
                }
                JSONValidator.validateObject(json);
                Dimensions dimensions= car.getDimensions();
                if (JSONValidator.isValid()) {
                    Map<String,Object> order=dimensions.getOrder();
                    if (order.containsKey(path[4])) {
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
                                throw new CommandException("Invalid path! Unable to find the element");
                        }
                    }
                    else
                        throw new CommandException("Invalid path! Path doesn't exist");
                }

            }
            else
                throw new CommandException("Invalid path! Unable to find the element");
        }
        else
            throw new CommandException("Invalid path! Unable to find the element");

    }
}
