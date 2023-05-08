package bg.tu_varna.sit.Parser;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Dimensions;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONtoCar extends JSONParser implements  JSONParserInterface{
    private Map<String, Object> json;

    public void parseJSON(Map<String, Object> json) {
        this.json = json;
    }

    @Override
    public Car parseJSON(String jsonString){
        this.json = parseObject(jsonString);
        return toCar();
    }

    public Car toCar(){
        String brand = null;
        String model = null;
        int year =0;
        boolean isElectric=false;

        if (json.containsKey("brand"))
            brand = (String) json.get("brand");
        if (json.containsKey("model"))
            model = (String) json.get("model");
        if (json.containsKey("year"))
            year = ((Number) json.get("year")).intValue();
        if (json.containsKey("isElectric"))
            isElectric = (Boolean) json.get("isElectric");

        List<Object> featuresArray;
        List<String> features = new ArrayList<>();
        if (json.containsKey("features")){
            featuresArray = (List<Object>) json.get("features");
            for (Object feature : featuresArray) {
                features.add((String) feature);
            }
        }

        JSONtoDimensions jsoNtoDimensions=new JSONtoDimensions();
        Dimensions dimensions;
        if (json.containsKey("dimensions")){
            jsoNtoDimensions.parseJSON((Map<String, Object>) json.get("dimensions"));
            dimensions=jsoNtoDimensions.toDimensions();
            json.replace("dimensions",dimensions);
        }
        else
            dimensions=new Dimensions();

        if (year<0)
            throw new IllegalArgumentException("Year should be a positive number");


        Object owner = json.get("owner");
        Car car=new Car(brand, model, year, isElectric, features, dimensions, owner);
        car.setOrder(json);
        return car;
    }

}
