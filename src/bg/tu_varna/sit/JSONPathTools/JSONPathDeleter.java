package bg.tu_varna.sit.JSONPathTools;

import bg.tu_varna.sit.commands.CommandException;

public interface JSONPathDeleter {
    void deletePath(String[] path) throws CommandException;
}
