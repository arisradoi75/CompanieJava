import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing car data.
 * Handles reading from and writing to a JSON file.
 */
public class CarService {

    // The path to the JSON file where car data is stored.
    private static final String CAR_FILE_PATH = "src/main/resources/car.json";
    // The Jackson object mapper for JSON serialization and deserialization.
    private ObjectMapper objectMapper;
    // The list of cars managed by this service.
    private List<Car> cars;

    /**
     * Constructor for the CarService.
     * Initializes the object mapper and loads the car data from the JSON file.
     */
    public CarService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.cars= readFromFile();
    }

    /**
     * Reads the list of cars from the JSON file.
     * @return The list of cars, or null if an error occurs.
     */
    public List<Car> readFromFile() {
        try {
            File file = new File(CAR_FILE_PATH);
            cars = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Car.class));
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes the given list of cars to the JSON file.
     * @param cars The list of cars to write.
     */
    public void writeToFile(List<Car>cars) {
        try {
            File file = new File(CAR_FILE_PATH);
            objectMapper.writeValue(file, cars);
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }
    // Saves the current state of the car list to the file.
    public void saveCurrentCars(){
        writeToFile(this.cars);
    }

    // Returns the list of cars.
    public List<Car> getCars() {
        return cars;
    }

    /**
     * Adds a new car to the list and saves the updated list to the file.
     * @param car The car to add.
     */
    public void addCar(Car car){
        this.cars.add(car);
        saveCurrentCars();
    }

    // Deletes a car by its ID using a lambda expression.
    public void deleteCar(String carId) {
        this.cars = this.cars.stream()
                .filter(car -> !car.getId().equals(carId))
                .collect(Collectors.toList());
        saveCurrentCars();
    }

}
