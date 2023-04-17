package bg.tu_varna.sit.commands;


import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.Parser.JSONValidator;
import bg.tu_varna.sit.Parser.JSONtoGarage;
import bg.tu_varna.sit.jsonio.JSONHandler;


public class ValidateCommand implements Command {
    private static boolean isValid = false;

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length > 1) {
            throw new CommandException("Unknown command");
        } else if (!OpenCommand.isItOpen()) {
            throw new CommandException("A file has yet to be opened");
        } else {
            try {
                JSONValidator.initialize();
                JSONValidator.validateObject(JSONHandler.getJsonString());
                isValid=JSONValidator.isValid();
                if (isValid){
                    JSONtoGarage jsoNtoGarage=new JSONtoGarage();
                    jsoNtoGarage.parseJSON(JSONHandler.getJsonString());
                }
            } catch (JSONException | IllegalArgumentException e) {
                System.out.println(e);
            }

        }
    }

    public static void setIsValid(boolean isValid) {
        ValidateCommand.isValid = isValid;
    }

    public static boolean isItValid() {
        return isValid;
    }
}