package bg.tu_varna.sit;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Car {
    private String brand;
    private String model;
    private int year;
    private boolean isElectric;
    private List<String> features;
    private Dimensions dimensions;
    private Object owner;
    private Map<String,Object> order;

    public Car(String brand, String model, int year, boolean isElectric, List<String> features, Dimensions dimensions, Object owner) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.isElectric = isElectric;
        this.features = features;
        this.dimensions = dimensions;
        this.owner = owner;
        order=new LinkedHashMap<>();
    }
/*
    public void addToOrder(String key,Object value){
        order.put(key,value);
    }*/

    public void setOrder(Map<String, Object> order) {
        this.order = order;
    }

    public Map<String, Object> getOrder() {
        return order;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public List<String> getFeatures() {
        return features;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public Object getOwner() {
        return owner;
    }


    public void setBrand(String brand) {
        this.brand = brand;
        if (order.containsKey("brand"))
            order.replace("brand",brand);
        else
            order.put("brand",brand);
    }

    public void setModel(String model) {
        this.model = model;
        if (order.containsKey("model"))
            order.replace("model",model);
        else
            order.put("model",model);
    }

    public void setYear(int year) {
        this.year = year;
        if (order.containsKey("year"))
            order.replace("year",year);
        else
            order.put("year",year);
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
        if (order.containsKey("electric"))
            order.replace("electric",electric);
        else
            order.put("electric",electric);
    }

    public void setFeatures(List<String> features) {
        this.features = features;
        if (order.containsKey("features"))
            order.replace("features",features);
        else
            order.put("features",features);
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
        if (order.containsKey("dimensions"))
            order.replace("dimensions",dimensions);
        else
            order.put("dimensions",dimensions);
    }

    public void setOwner(Object owner) {
        this.owner = owner;
        if (order.containsKey("owner"))
            order.replace("owner",owner);
        else
            order.put("owner",owner);
    }


    @Override
    public String toString() {
        int i=1;
        StringBuilder sb=new StringBuilder();
        sb.append("\nBrand: ");
        if (brand==null || brand.isEmpty())
            sb.append("Not specified");
        else
            sb.append(brand);
        sb.append("\nModel: ");
        if (model==null || model.isEmpty())
            sb.append("Not specified");
        else
            sb.append(model);
        sb.append("\nYear: ");
        if (year==0)
            sb.append("Not specified");
        else
            sb.append(year);
        if (isElectric)
            sb.append("\nElectric");
        else
            sb.append("\nNot electric");
        sb.append("\nFeatures: ").append(features);
        sb.append("\nDimensions: ").append(dimensions);
        sb.append("\nOwner: ").append(owner);
        return sb.toString();
    }

    public String toJSON(){
        int i=1;

        StringBuilder sb=new StringBuilder();
        for (Map.Entry<String,Object> o: order.entrySet()) {
            sb.append("\n\t\t\"").append(o.getKey()).append("\": ");
            if (o.getValue() instanceof String && !o.getValue().equals("null")){
                sb.append("\"").append(o.getValue()).append("\"");
            }
            else if (o.getValue() instanceof List){
                sb.append("[");
                for (String list:features) {
                    sb.append("\"").append(list).append("\"");
                    if (features.indexOf(list)!=features.size()-1){
                        sb.append(",");
                    }
                }
                sb.append("]");
            }
            else if (o.getValue() instanceof Dimensions){
                sb.append(((Dimensions) o.getValue()).toJSON());
            }
            else
                sb.append((o.getValue()));

            if (i!= order.size()) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }
}
