package bg.tu_varna.sit;

import java.util.LinkedHashMap;
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
    public Dimensions(){
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
        if (order.containsKey("length"))
            order.replace("length",length);
        else
            order.put("length",length);
    }

    public void setWidth(double width) {
        this.width = width;
        if (order.containsKey("width"))
            order.replace("width",width);
        else
            order.put("width",width);
    }

    public void setHeight(double height) {
        this.height = height;
        if (order.containsKey("height"))
            order.replace("height",height);
        else
            order.put("height",height);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("{Length: ");
        if (length==0)
            sb.append("Not specified ");
        else
            sb.append(length);
        sb.append("\tWidth: ");
        if (width==0)
            sb.append("Not specified ");
        else
            sb.append(width);
        sb.append("\tHeight: ");
        if (height==0)
            sb.append("Not specified ");
        else
            sb.append(height);
        sb.append("}");
        return sb.toString();
    }

    public String toJSON(){
        int i=1;
        StringBuilder sb=new StringBuilder();
        sb.append("{");
        for (Map.Entry<String,Object> o: order.entrySet()) {
            sb.append("\n\t\t\t\"").append(o.getKey()).append("\": ").append(o.getValue());
            if (i!= order.size()) {
                sb.append(",");
            }
            i++;
        }
        sb.append("\n\t\t}");
        return sb.toString();
    }
}