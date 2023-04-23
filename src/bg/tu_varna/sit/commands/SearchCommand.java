package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Garage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchCommand implements Command{
    private List<Object> result=new ArrayList<>();
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length!=1) {
            throw new CommandException("Unknown command");
        } else if (!OpenCommand.isItOpen()) {
            throw new CommandException("A file has yet to be opened");
        } else if (!ValidateCommand.isItValid()){
            throw new CommandException("File contents have not been validated or are not valid");
        }
        else{
            Map<String,Car> search=Garage.getInstance().getCarMap();

            if (search.containsKey(args[1]))
                result.add(search.get(args[1]));
            else{
                if (args[1].equals("garage"))
                    if (search.isEmpty())
                        throw new CommandException("No such keys");
                    else
                        result.add(Garage.getInstance().toJSON());
                else{
                    Map<String,Object> order;
                    for (Car a: Garage.getInstance().getCarMap().values()) {
                        order=a.getOrder();
                        if (order.containsKey(args[1])){
                            result.add(order.get(args[1]));
                        }
                        else{
                            order=a.getDimensions().getOrder();
                            if (order.containsKey(args[1])){
                                result.add(order.get(args[1]));
                            }
                        }
                    }
                }

            }


            if (result.isEmpty()){
                throw new CommandException("No such keys");
                // System.out.println("No such keys");
            }
            else{
                System.out.println("Every value of key: "+args[1]);
                for (Object str:result) {
                    System.out.println(str);
                }

                result.clear();
            }
        }
    }
}
