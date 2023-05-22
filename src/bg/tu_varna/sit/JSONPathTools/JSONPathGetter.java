package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.commands.CommandException;

public interface JSONPathGetter {
    String getElement(String[] path) throws CommandException;
}
