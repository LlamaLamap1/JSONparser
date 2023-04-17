package bg.tu_varna.sit.jsonio;

import java.io.FileReader;
import java.io.IOException;

public class ReadJSON {
    public static String readFile(String directory) throws IOException{
        FileReader reader=new FileReader(directory);

        StringBuilder stringBuilder=new StringBuilder();
        int character;

        while ((character = reader.read()) != -1) {
            stringBuilder.append((char) character);
        }

        reader.close();
        return stringBuilder.toString();
    }
}
