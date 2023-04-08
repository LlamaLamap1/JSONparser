package bg.tu_varna.sit;

import java.util.ArrayList;
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
        order.replace("brand",brand);
    }

    public void setModel(String model) {
        this.model = model;
        order.replace("model",model);
    }

    public void setYear(int year) {
        this.year = year;
        order.replace("year",year);
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
        order.replace("electric",electric);
    }

    public void setFeatures(List<String> features) {
        this.features = features;
        order.replace("features",features);
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
        order.replace("dimensions",dimensions);
    }

    public void setOwner(Object owner) {
        this.owner = owner;
        order.replace("owner",owner);
    }


    @Override
    public String toString() {
        int i=1;

        StringBuilder sb=new StringBuilder();
        for (Map.Entry<String,Object> o: order.entrySet()) {
            sb.append("\n\"").append(o.getKey()).append("\": ");
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
        sb.append('\n');
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
