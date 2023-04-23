package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.commands.CommandException;

public interface JSONPathSetter {
    void setPath(String[] path, String json) throws CommandException, JSONException;
}
