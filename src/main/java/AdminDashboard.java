import javax.swing.*;
import java.awt.*;

/**
 * The window displayed to users with the "ADMIN" role.
 * Allows viewing, adding, and deleting cars.
 */
public class AdminDashboard extends JFrame {

    // Service for handling car-related operations.
    private final CarService carService;
    // UI component to display the list of cars.
    private JList<Car> carList;
    // The model for the car list.
    private DefaultListModel<Car> listModel;

    // Fields for adding a new car.
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField priceField;
    private JButton addButton;
    // Button to delete a selected car.
    private JButton deleteButton;
    // Button to log out.
    private JButton logoutButton;
    // Button to go back to the login screen.
    private JButton backButton;

    /**
     * Constructor for the AdminDashboard.
     * @param carService The service for car operations.
     */
    public AdminDashboard(CarService carService) {
        this.carService = carService;
        initUI();
        loadCars();
    }

    /**
     * Initializes and configures the UI components.
     */
    private void initUI() {
        setTitle("Administrator Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen.

        // Main layout (BorderLayout).
        setLayout(new BorderLayout(10, 10));
        // Add general padding.
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel (North) for navigation buttons.
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutButton = new JButton("Logout");
        backButton = new JButton("Back");
        topPanel.add(backButton);
        topPanel.add(logoutButton);
        add(topPanel, BorderLayout.NORTH);

        // 1. Panel with the list of cars (Center).
        listModel = new DefaultListModel<>();
        carList = new JList<>(listModel);
        carList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(carList);
        add(scrollPane, BorderLayout.CENTER);

        // 2. Bottom panel (South) for adding and deleting.
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));

        // Add panel (in the center of the bottom panel).
        JPanel addPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add New Car"));

        addPanel.add(new JLabel("Make:"));
        makeField = new JTextField();
        addPanel.add(makeField);

        addPanel.add(new JLabel("Model:"));
        modelField = new JTextField();
        addPanel.add(modelField);

        addPanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        addPanel.add(yearField);

        addPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        addPanel.add(priceField);

        addButton = new JButton("Add");
        addPanel.add(addButton);
        addPanel.add(new JLabel()); // Empty placeholder for alignment.

        bottomPanel.add(addPanel, BorderLayout.CENTER);

        // Delete panel (in the south of the bottom panel).
        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        deleteButton = new JButton("Delete Selected Car");
        deleteButton.setBackground(new Color(220, 50, 50)); // Red button.
        deleteButton.setForeground(Color.WHITE);
        deletePanel.add(deleteButton);
        bottomPanel.add(deletePanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions.
        addButton.addActionListener(e -> addCar());
        deleteButton.addActionListener(e -> deleteCar());
        logoutButton.addActionListener(e -> onLogout());
        backButton.addActionListener(e -> onLogout());
    }

    /**
     * Loads the cars from the service into the list.
     */
    private void loadCars() {
        listModel.clear();
        carService.getCars().forEach(listModel::addElement);
    }

    /**
     * Action for adding a car.
     */
    private void addCar() {
        try {
            // Note: The car's ID is currently set to its make. This might not be a suitable strategy for a unique identifier.
            String id = makeField.getText();
            String make = makeField.getText();
            String model = modelField.getText();
            int year = Integer.parseInt(yearField.getText());
            double price = Double.parseDouble(priceField.getText());

            if (make.isEmpty() || model.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Make and model are mandatory.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Car newCar = new Car(id,make, model, year, price);
            carService.addCar(newCar);
            loadCars(); // Reload the list.

            // Clear the fields after adding.
            makeField.setText("");
            modelField.setText("");
            yearField.setText("");
            priceField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year and price must be valid numbers.", "Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Action for deleting the selected car.
     */
    private void deleteCar() {
        Car selectedCar = carList.getSelectedValue();
        if (selectedCar == null) {
            JOptionPane.showMessageDialog(this, "Please select a car from the list to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ask for confirmation.
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the car: \n" + selectedCar + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            carService.deleteCar(selectedCar.getId());
            loadCars(); // Reload the list.
        }
    }

    /**
     * Handles the logout action by closing the current window
     * and opening a new LoginFrame.
     */
    private void onLogout() {
        this.dispose(); // Close the current window.
        // Open a new login window.
        new LoginFrame(new UserService(), carService).setVisible(true);
    }
}
