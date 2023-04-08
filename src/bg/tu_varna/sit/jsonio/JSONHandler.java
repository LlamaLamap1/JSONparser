package bg.tu_varna.sit.jsonio;


import bg.tu_varna.sit.Parser.JSONException;

import java.io.File;
import java.io.IOException;

public class JSONHandler {
    private static String directory;
    private static String jsonString;

    public static void handleJSON(String directory) throws IOException, JSONException {
        if (!(directory.endsWith(".json")))
            directory+=".json";

        setDirectory(directory);

        File file=new File(directory);
        if (!file.exists())
            WriteJSON.writeJSON(directory,null);

        jsonString=ReadJSON.readFile(directory);
    }

    public static String getDirectory() {
        return directory;
    }

    public static String getJsonString() {
        return jsonString;
    }

    public static void setDirectory(String directory) {
        JSONHandler.directory = directory;
    }

    public static void setJsonString(String jsonString) {
        JSONHandler.jsonString = jsonString;
    }
}
