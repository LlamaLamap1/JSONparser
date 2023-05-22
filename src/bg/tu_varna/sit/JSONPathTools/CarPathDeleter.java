package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.commands.CommandException;


import java.util.Map;

public class CarPathDeleter implements JSONPathDeleter{
    @Override
    public void deletePath(String[] path) throws CommandException {
        Garage garage=Garage.getInstance();
        switch (path.length){
            case 3:
                if (garage.getCarMap().containsKey(path[2])){
                    garage.getCarMap().remove(path[2]);
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


                    Map <String,Object> order=car.getOrder();

                    if (order.containsKey(path[3])){
                        switch (path[3]) {
                            case "brand":
                                car.setBrand(null);
                                order.remove(path[3]);
                                car.setOrder(order);
                                break;
                            case "model":
                                car.setModel(null);
                                order.remove(path[3]);
                                car.setOrder(order);
                                break;
                            case "year":
                                car.setYear(0);
                                order.remove(path[3]);
                                car.setOrder(order);
                                break;
                            case "isElectric":
                                car.setElectric(false);
                                order.remove(path[3]);
                                car.setOrder(order);
                                break;
                            case "features":
                                car.setFeatures(null);
                                order.remove(path[3]);
                                car.setOrder(order);
                                break;
                            case "dimensions":
                                car.setDimensions(null);
                                order.remove(path[3]);
                                car.setOrder(order);
                                break;
                            case "owner":
                                car.setOwner(null);
                                order.remove(path[3]);
                                car.setOrder(order);
                                break;
                            default:
                                throw new CommandException("Invalid path! Unable to find the element");
                        }
                    }
                    else
                        throw new CommandException("Invalid path! Path doesn't exist");

                }
                break;
            case 5:

                DimensionsPathDeleter dimensionsPathDeleter = new DimensionsPathDeleter();
                dimensionsPathDeleter.deletePath(path);

                break;
        }
    }

}
