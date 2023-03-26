package bg.tu_varna.sit;

import java.util.List;

public class Car {
    private String brand;
    private String model;
    private int year;
    private boolean isElectric;
    private List<String> features;
    private Dimensions dimensions;
    private Object owner;

    public Car(String make, String brand, int year, boolean isElectric, List<String> features, Dimensions dimensions, Object owner) {
        this.brand = make;
        this.model = brand;
        this.year = year;
        this.isElectric = isElectric;
        this.features = features;
        this.dimensions = dimensions;
        this.owner = owner;
    }

    public String getMake() { return brand; }
    public void setMake(String make) { this.brand = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public boolean isElectric() { return isElectric; }
    public void setElectric(boolean isElectric) { this.isElectric = isElectric; }
    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }
    public Dimensions getDimensions() { return dimensions; }
    public void setDimensions(Dimensions dimensions) { this.dimensions = dimensions; }
    public Object getOwner() { return owner; }
    public void setOwner(Object owner) { this.owner = owner; }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", isElectric=" + isElectric +
                ", features=" + features +
                ", dimensions=" + dimensions +
                ", owner=" + owner +
                '}';
    }
}
