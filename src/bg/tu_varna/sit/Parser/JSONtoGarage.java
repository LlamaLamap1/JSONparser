package bg.tu_varna.sit.Parser;

import bg.tu_varna.sit.Car;
import bg.tu_varna.sit.Garage;


import java.util.LinkedHashMap;
import java.util.Map;

public class JSONtoGarage extends JSONParser implements  JSONParserInterface{

    private Map<String, Object> json;

    public void parseJSON(Map<String, Object> json) {
        this.json = json;
    }

    @Override
    public Garage parseJSON(String jsonString) throws JSONException {
        this.json = parseObject(jsonString);

        return toGarage();
    }

    public Garage toGarage(){
        Map<String, Object> garageObject =new LinkedHashMap<>();
        if (json.containsKey("garage"))
            garageObject=(Map<String, Object>) json.get("garage");
        Garage garage=new Garage();
        JSONtoCar jsoNtoCar=new JSONtoCar();

        for (Map.Entry<String,Object> o: garageObject.entrySet()){
            jsoNtoCar.parseJSON((Map<String, Object>) garageObject.get(o.getKey()));
            Car car=jsoNtoCar.toCar();
            garage.addCar(o.getKey(),car);
        }
        return garage;
    }
}
