package bg.tu_varna.sit.Parser;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Dimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONtoCar extends JSONParser {
    @Override
    public Car parseJSON(String jsonString) throws JSONException{
        Map<String, Object> json = parseObject(jsonString);
        Map<String, Object> carObject = (Map<String, Object>) json.get("car");

        String make = (String) carObject.get("make");
        String model = (String) carObject.get("model");
        int year = ((Number) carObject.get("year")).intValue();
        boolean isElectric = (Boolean) carObject.get("isElectric");

        List<String> features = new ArrayList<>();
        List<Object> featuresArray = (List<Object>) carObject.get("features");
        for (Object feature : featuresArray) {
            features.add((String) feature);
        }

        Map<String, Object> dimensionsObject = (Map<String, Object>) carObject.get("dimensions");
        double length = ((Number) dimensionsObject.get("length")).doubleValue();
        double width = ((Number) dimensionsObject.get("width")).doubleValue();
        double height = ((Number) dimensionsObject.get("height")).doubleValue();

        Dimensions dimensions = new Dimensions(length, width, height);

        Object owner = carObject.get("owner");

        return new Car(make, model, year, isElectric, features, dimensions, owner);
    }


}
