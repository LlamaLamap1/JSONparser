package bg.tu_varna.sit.commands;

import bg.tu_varna.sit.Garage;
import bg.tu_varna.sit.JSONPathTools.GaragePathGetter;
import bg.tu_varna.sit.JSONPathTools.JSONPathGetter;
import bg.tu_varna.sit.jsonio.JSONHandler;
import bg.tu_varna.sit.jsonio.WriteJSON;

import java.io.IOException;

public class SaveAsCommand  implements Command {
    @Override
    public void execute(String[] args) throws CommandException {
        try {
            if (!OpenCommand.isItOpen()) {
                throw new CommandException("A file has yet to be opened");
            } else if (!ValidateCommand.isItValid()){
                throw new CommandException("File contents have not been validated or are not valid");
            }
            else{
                String[] arguments=args[1].split(" ",2);

                if (!arguments[0].endsWith(".json"))
                    arguments[0]+=".json";

                JSONHandler.setDirectory(arguments[0]);

                if  (arguments.length==2){
                    String[] path=arguments[1].split("\\.");
                    if (!path[0].equals("$"))
                        throw new CommandException("Path should start with $. and should have a structure like $.[element1].[element2]");
                    if (path.length==1)
                        JSONHandler.setJsonString("{}");
                    else{
                        JSONPathGetter pathGetter=new GaragePathGetter();
                        String json= pathGetter.getElement(path);
                        StringBuilder sb = new StringBuilder(json);
                        for (int i=path.length-2;i>=1 ;i--){
                            if (path[i].equals("dimensions") || path[i].equals("garage")){
                                sb.insert(1, "\"" + path[i] + "\":{").append("}");
                                continue;
                            }
                            if (i==2)
                                sb.insert(1, "\"" + path[i] + "\":{").append("}");
                            else
                                sb.insert(1, "\"" + path[i] + "\":");
                        }
                        JSONHandler.setJsonString(sb.toString());
                    }
                }
                else{
                    JSONHandler.setJsonString(Garage.getInstance().toJSON());
                }
                WriteJSON.writeJSON(JSONHandler.getDirectory(),JSONHandler.getJsonString());
                //ValidateCommand.setIsValid(false);
                //OpenCommand.setIsOpen();
            }
        }
        catch ( IllegalArgumentException | IOException e) {
            System.out.println(e);
        }
    }


}