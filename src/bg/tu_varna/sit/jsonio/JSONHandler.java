package bg.tu_varna.sit.jsonio;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Parser.JSONException;
import bg.tu_varna.sit.Parser.JSONParser;
import bg.tu_varna.sit.Parser.JSONtoCar;

import java.io.File;
import java.io.IOException;

public class JSONHandler {

    public static void handleJSON(String directory) throws IOException, JSONException {
        if (!(directory.endsWith(".json")))
            directory+=".json";

        File file=new File(directory);
        if (!file.exists())
            WriteJSON.writeJSON(directory,null);

        JSONParser parser=new JSONtoCar();
        Car car= ((JSONtoCar) parser).parseJSON(ReadJSON.readFile(directory));
        System.out.println(car);
    }
}
