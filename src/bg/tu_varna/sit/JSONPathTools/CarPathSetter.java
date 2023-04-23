package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.Parser.*;
import bg.tu_varna.sit.commands.CommandException;

import java.util.List;
import java.util.Map;

public class CarPathSetter implements JSONPathSetter{
    @Override
    public void setPath(String[] path, String json) throws CommandException, JSONException {
        Garage garage=Garage.getInstance();
        JSONValidator.initialize();
        switch (path.length){
            case 3:
                JSONValidator.validateObject(json);
                if (JSONValidator.isValid() && garage.getCarMap().containsKey(path[2])){
                    JSONtoCar jsoNtoCar=new JSONtoCar();
                    Car car=jsoNtoCar.parseJSON(json);
                    garage.setCar(path[2],car);
                }
                else if (!garage.getCarMap().containsKey(path[2]))
                    throw new CommandException("Invalid path! Unable to find the element");
                break;
            case 4:
                if (garage.getCarMap().containsKey(path[2])){
                    Car car=garage.getCarMap().get(path[2]);
                    JSONParser jsonParser=new JSONParser();
                    if (car.getOrder().containsKey(path[3])) {
                        if (!json.contains(path[3])) {
                            StringBuilder sb = new StringBuilder(json);
                            if (path[3].equals("dimensions"))
                                sb.insert(1, "\"" + path[3] + "\":{").append("}");
                            else
                                sb.insert(1, "\"" + path[3] + "\":");
                            json = sb.toString();
                        }
                        JSONValidator.validateObject(json);
                    }
                    else
                        throw new CommandException("Invalid path! Unable to find the element");
                    if (JSONValidator.isValid()){
                        Map <String,Object> order=car.getOrder();
                        if (order.containsKey(path[3])){
                            switch (path[3]) {
                                case "brand":

                                    car.setBrand((String) jsonParser.parseObject(json).get(path[3]));
                                    break;
                                case "model":
                                    car.setModel((String) jsonParser.parseObject(json).get(path[3]));
                                    break;
                                case "year":
                                    car.setYear((Integer) jsonParser.parseObject(json).get(path[3]));
                                    break;
                                case "isElectric":
                                    car.setElectric((Boolean) jsonParser.parseObject(json).get(path[3]));
                                    break;
                                case "features":
                                    car.setFeatures((List<String>) jsonParser.parseObject(json).get(path[3]));
                                    break;
                                case "dimensions":
                                    JSONtoDimensions jsoNtoDimensions = new JSONtoDimensions();
                                    jsoNtoDimensions.parseJSON((Map<String, Object>) jsonParser.parseObject(json).get(path[3]));
                                    car.setDimensions(jsoNtoDimensions.toDimensions());
                                    break;
                                case "owner":
                                    car.setOwner(jsonParser.parseObject(json).get(path[3]));
                                    break;
                                default:
                                    throw new CommandException("Invalid path! Unable to find the element");
                            }
                        }
                        else
                            throw new CommandException("Invalid path! Path doesn't exist");
                    }
                }
                break;
            case 5:

                DimensionsPathSetter dimensionPathSetter = new DimensionsPathSetter();
                dimensionPathSetter.setPath(path, json);

                break;
        }
    }

}
