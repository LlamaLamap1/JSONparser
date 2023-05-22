package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.commands.CommandException;

public interface JSONPathMover {
    void moveElement(String[] from, String[] to) throws CommandException, JSONException;
}
