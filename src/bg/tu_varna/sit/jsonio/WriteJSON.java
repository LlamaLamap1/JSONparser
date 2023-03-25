package bg.tu_varna.sit.jsonio;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJSON {
    public static void writeJSON(String path, String jsonContent) throws IOException {
        FileWriter fileWriter = new FileWriter(path);

        if (jsonContent==null || jsonContent.length()==0)
            fileWriter.write("{}");
        else
            fileWriter.write(jsonContent);

        fileWriter.close();
    }
}
