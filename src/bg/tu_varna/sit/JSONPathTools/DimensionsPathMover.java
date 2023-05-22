package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.commands.CommandException;

import java.util.Arrays;

public class DimensionsPathMover implements JSONPathMover{
    @Override
    public void moveElement(String[] from, String[] to) throws CommandException, JSONException {
        Garage garage= Garage.getInstance();
        String[] prev= Arrays.copyOf(from, from.length);
        if (to[2].equals(from[2]))
            throw new CommandException("Can't move dimensions elements within the same car. The car needs to be different");
        if (garage.getCarMap().containsKey(to[2])){
            JSONPathGetter pathGetter=new GaragePathGetter();
            String json=pathGetter.getElement(from);
            from[2]=to[2];
            if (garage.getCarMap().containsKey(to[3])){

                JSONPathSetter pathSetter=new GaragePathSetter();

                pathSetter.setPath(from,json);
            }
            else  {
                StringBuilder sb = new StringBuilder(json);
                sb.insert(1, "{\"" + from[3] + "\":{").append("}");
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
            StringBuilder sb = new StringBuilder(json);
            sb.insert(1, "\"" + from[2] + "\":{\"" + from[3] + "\":{").append("}}");

            JSONPathCreator pathCreator=new GaragePathCreator();

            pathCreator.createPath(from,json);
            JSONPathDeleter pathDeleter=new GaragePathDeleter();
            pathDeleter.deletePath(prev);
        }
    }
}
