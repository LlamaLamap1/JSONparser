package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.commands.CommandException;

import java.util.Arrays;

public class CarPathMover implements JSONPathMover{
    @Override
    public void moveElement(String[] from, String[] to) throws CommandException, JSONException {
        Garage garage= Garage.getInstance();
        if (from.length==3)
            throw new CommandException("The structure requires to move paths of deeper levels ");

        if (!garage.getCarMap().containsKey(from[2]) || !garage.getCarMap().get(from[2]).getOrder().containsKey(from[3]))
            throw new CommandException("Invalid path to element");

        String[] prev=Arrays.copyOf(from, from.length);
        switch (from.length){
            case 4:
                if (to[2].equals(from[2]))
                    throw new CommandException("Can't move elements to the same parent as where we are getting the element");
                if (garage.getCarMap().containsKey(to[2])){
                    JSONPathGetter pathGetter=new GaragePathGetter();
                    String json=pathGetter.getElement(from);

                    from[2]=to[2];
                    if (garage.getCarMap().get(to[2]).getOrder().containsKey(from[3])){
                        JSONPathSetter pathSetter=new GaragePathSetter();

                        pathSetter.setPath(from,json);
                    }
                    else {
                        JSONPathCreator pathCreator=new GaragePathCreator();



                        pathCreator.createPath(from,json);
                    }
                    JSONPathDeleter pathDeleter=new GaragePathDeleter();
                    pathDeleter.deletePath(prev);
                }
                else{
                    JSONPathGetter pathGetter=new GaragePathGetter();
                    String json=pathGetter.getElement(from);
                    from[2]=to[2];
                    JSONPathCreator pathCreator=new GaragePathCreator();


                    pathCreator.createPath(from,json);
                    JSONPathDeleter pathDeleter=new GaragePathDeleter();
                    pathDeleter.deletePath(prev);
                }
                break;

            case 5:
                DimensionsPathMover dimensionPathMover=new DimensionsPathMover();
                dimensionPathMover.moveElement(from,to);
        }
    }
}
