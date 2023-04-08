package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dimensions {
    private double length;
    private double width;
    private double height;
    private Map<String,Object> order;


    public Dimensions(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
        order=new LinkedHashMap<>();
    }
    public void setOrder(Map<String, Object> order) {
        this.order = order;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Map<String, Object> getOrder() {
        return order;
    }


    public void setLength(double length) {
        this.length = length;
        order.replace("length",length);
    }

    public void setWidth(double width) {
        this.width = width;
        order.replace("width",width);
    }

    public void setHeight(double height) {
        this.height = height;
        order.replace("height",height);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (Map.Entry<String,Object> o: order.entrySet()) {
            sb.append("\n\"").append(o.getKey()).append("\": ").append(o.getValue());
        }
        sb.append('\n');
        return sb.toString();
    }

    public String toJSON(){
        StringBuilder sb=new StringBuilder();
        sb.append("{");
        for (Map.Entry<String,Object> o: order.entrySet()) {
            sb.append("\n\t\t\t\"").append(o.getKey()).append("\": ").append(o.getValue());
        }
        sb.append("\n\t\t}");
        return sb.toString();
    }
}