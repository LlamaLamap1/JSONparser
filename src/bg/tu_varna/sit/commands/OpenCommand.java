package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.jsonio.JSONHandler;


import java.io.IOException;

public class OpenCommand implements Command {
    private static boolean isOpen=false;
    @Override
    public void execute(String[] args) throws CommandException{
        try {
            if (args.length!=1){
                if (!isOpen){
                    String path=args[1];


                    JSONHandler.handleJSON(path);

                    setIsOpen();
                }
                else
                    throw new CommandException("There is a file that is already opened");

            }
            else
                throw new CommandException("This command needs file path as argument");
        }
        catch (IOException e){
            System.out.println("Couldn't find the path to the file.");
        }
        catch (JSONException e){
            System.out.println(e);
        }
    }

    public static void setIsOpen() {
        isOpen= !isOpen;
    }
    public static boolean isItOpen(){
        return isOpen;
    }
}
