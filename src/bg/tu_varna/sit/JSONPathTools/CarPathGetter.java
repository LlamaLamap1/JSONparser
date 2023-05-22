package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.commands.CommandException;

import java.util.Map;

public class CarPathGetter implements JSONPathGetter{
    @Override
    public String getElement(String[] path) throws CommandException {
        Garage garage=Garage.getInstance();
        switch (path.length){
            case 3:
                if (garage.getCarMap().containsKey(path[2])){
                    return  "{\""+path[2]+"\":{"+garage.getCarMap().get(path[2]).toJSON()+"}}";
                }
                else if (!garage.getCarMap().containsKey(path[2]))
                    throw new CommandException("Invalid path! Unable to find the element");
                break;
            case 4:
                if (garage.getCarMap().containsKey(path[2])){
                    Car car=garage.getCarMap().get(path[2]);
                    if (!car.getOrder().containsKey(path[3])) {
                        throw new CommandException("Invalid path! Unable to find the element");
                    }


                    Map<String,Object> order=car.getOrder();

                    if (order.containsKey(path[3])){
                        switch (path[3]) {
                            case "brand":
                                return  "{\""+path[3]+"\":\""+car.getBrand()+"\"}";
                            case "model":
                                return  "{\""+path[3]+"\":\""+car.getModel()+"\"}";
                            case "year":
                                return  "{\""+path[3]+"\":"+car.getYear()+"}";
                            case "isElectric":
                                return  "{\""+path[3]+"\":"+car.isElectric()+"}";
                            case "features":
                                return  "{\""+path[3]+"\":"+car.featuresToJSON()+"}";
                            case "dimensions":
                                return  "{\""+path[3]+"\":{"+car.getDimensions().toJSON()+"}}";
                            case "owner":
                                return  "{\""+path[3]+"\":\""+car.getOwner()+"\"}";
                            default:
                                throw new CommandException("Invalid path! Unable to find the element");
                        }
                    }
                    else
                        throw new CommandException("Invalid path! Path doesn't exist");

                }
                break;
            case 5:

                DimensionsPathGetter dimensionsPathGetter = new DimensionsPathGetter();
                return dimensionsPathGetter.getElement(path);
        }
        throw new CommandException("Invalid path! Path doesn't exist");

    }

}
