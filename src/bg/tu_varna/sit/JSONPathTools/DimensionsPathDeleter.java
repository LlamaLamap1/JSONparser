package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Dimensions;
import bg.tu_varna.sit.Garage;

import bg.tu_varna.sit.commands.CommandException;

import java.util.Map;

public class DimensionsPathDeleter implements JSONPathDeleter{
    @Override
    public void deletePath(String[] path) throws CommandException {
        Garage garage=Garage.getInstance();
        if (garage.getCarMap().containsKey(path[2])){
            Car car=garage.getCarMap().get(path[2]);
            if (car.getOrder().containsKey(path[3])){

                Dimensions dimensions= car.getDimensions();

                Map<String,Object> order=dimensions.getOrder();

                if (order.containsKey(path[4])) {

                    switch (path[4]) {
                        case "length":
                            dimensions.setLength(0);
                            order.remove(path[4]);
                            dimensions.setOrder(order);
                            break;
                        case "height":
                            dimensions.setHeight(0);
                            order.remove(path[4]);
                            dimensions.setOrder(order);
                            break;
                        case "width":
                            dimensions.setWidth(0);
                            order.remove(path[4]);
                            dimensions.setOrder(order);
                            break;
                        default:
                            throw new CommandException("Invalid path! Unable to find the element");
                    }
                }
                else
                    throw new CommandException("Invalid path! Path doesn't exist");


            }
            else
                throw new CommandException("Invalid path! Unable to find the element");
        }
        else
            throw new CommandException("Invalid path! Unable to find the element");

    }
}
