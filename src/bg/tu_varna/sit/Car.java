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

    public Car() {
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
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

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