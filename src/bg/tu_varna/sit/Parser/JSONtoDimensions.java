package bg.tu_varna.sit.Parser;


import bg.tu_varna.sit.Dimensions;


import java.util.Map;

public class JSONtoDimensions extends JSONParser implements  JSONParserInterface{
    private Map<String, Object> json;

    public void parseJSON(Map<String, Object> json) {
        this.json = json;
    }

    @Override
    public Dimensions parseJSON(String jsonString){
        this.json = parseObject(jsonString);
        return toDimensions();
    }

    public Dimensions toDimensions(){
        double length = 0;
        double width = 0;
        double height = 0;


        if (json.containsKey("length"))
            length = ((Number) json.get("length")).doubleValue();
        if (json.containsKey("width"))
            width = ((Number) json.get("width")).doubleValue();
        if (json.containsKey("height"))
            height = ((Number) json.get("height")).doubleValue();

        if (length<0 || height<0 || width<0)
            throw new IllegalArgumentException("Negative dimensions not supported");

        Dimensions dimensions = new Dimensions(length, width, height);

        dimensions.setOrder(json);
        return dimensions;
    }
}
