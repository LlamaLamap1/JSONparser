package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.jsonio.JSONHandler;


import java.io.IOException;

public class OpenCommand implements Command{
    private static boolean isOpen=false;
    @Override
    public void execute(String[] args){
        try {
            if (args.length>1){
                if (!isOpen){
                    String path=args[1];


                    JSONHandler.handleJSON(path);

                    setIsOpen();
                }
                else
                    System.out.println("File is already opened");

            }
            else
                System.out.println("This command needs file path as argument");
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
