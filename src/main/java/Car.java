/**
 * Represents a car with its properties.
 */
public class Car {
    // The unique identifier for the car.
    private String id;
    private String brand;
    private String model;
    private int year;
    private double price;

    public Car() {
    }

    /**
     * Constructor for creating a Car object.
     * @param id The unique identifier for the car.
     * @param brand The brand of the car.
     * @param model The model of the car.
     * @param year The manufacturing year of the car.
     * @param price The price of the car.
     */
    public Car(String id,String brand, String model, int year, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    // Getter for the car's ID.
    public String getId() {
        return id;
    }

    // Setter for the car's ID.
    public void setId(String id) {
        this.id = id;
    }

    // Getter for the car's brand.
    public String getBrand() {
        return brand;
    }

    // Setter for the car's brand.
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter for the car's model.
    public String getModel() {
        return model;
    }

    // Setter for the car's model.
    public void setModel(String model) {
        this.model = model;
    }

    // Getter for the car's manufacturing year.
    public int getYear() {
        return year;
    }

    // Setter for the car's manufacturing year.
    public void setYear(int year) {
        this.year = year;
    }

    // Getter for the car's price.
    public double getPrice() {
        return price;
    }

    // Setter for the car's price.
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the car.
     * @return A formatted string with the car's details.
     */
    @Override
    public String toString() {
        return year + " " + brand + " " + model + " (" + price + " EUR)";
    }

}
