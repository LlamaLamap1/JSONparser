package bg.tu_varna.sit.commands;


import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.Parser.JSONValidator;
import bg.tu_varna.sit.Parser.JSONtoGarage;
import bg.tu_varna.sit.jsonio.JSONHandler;


public class ValidateCommand implements Command {
    private static boolean isValid = false;

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Unknown command");
        } else if (!OpenCommand.isItOpen()) {
            System.out.println("File is not opened");
        } else {
            try {
                JSONValidator.initialize();
                JSONValidator.validateObject(JSONHandler.getJsonString());
                isValid=JSONValidator.isValidator();
                if (isValid){
                    JSONtoGarage jsoNtoCar=new JSONtoGarage();
                    jsoNtoCar.parseJSON(JSONHandler.getJsonString());
                }
            } catch (JSONException e) {
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