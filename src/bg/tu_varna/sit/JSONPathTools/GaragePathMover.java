package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.commands.CommandException;

public class GaragePathMover implements JSONPathMover{

    @Override
    public void moveElement(String[] from, String[] to) throws CommandException, JSONException {
        if (from.length==2)
            throw new CommandException("Can't move garage element");
        else if (!from[1].equals("garage") || !to[1].equals("garage"))
            throw new CommandException("Invalid path: Both paths should contain garage after the $. element");
        else{
            CarPathMover carPathMover=new   CarPathMover();
            carPathMover.moveElement(from,to);
        }

    }
}
