package bg.tu_varna.sit.Parser;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Dimensions;
import bg.tu_varna.sit.Garage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONtoGarage extends JSONParser {
    @Override
    public void parseJSON(String jsonString) throws JSONException{
        Map<String, Object> json = parseObject(jsonString);
        Map<String, Object> garageObject = (Map<String, Object>) json.get("garage");
        Map<String, Object> carObject;
        Garage garage=new Garage();

        for (Map.Entry<String,Object> o: garageObject.entrySet()){
            carObject= (Map<String, Object>) garageObject.get(o.getKey());
            String make = (String) carObject.get("brand");
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
            dimensions.setOrder(dimensionsObject);

            carObject.put("dimensions",dimensions);

            Object owner = carObject.get("owner");

            Car car=new Car(make, model, year, isElectric, features, dimensions, owner);
            car.setOrder(carObject);
            garage.addCar(o.getKey(),car);
        }

    }
}
