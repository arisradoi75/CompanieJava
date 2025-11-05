import role.Role;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

/**
 * The main login window.
 * It is a JFrame (a basic window).
 */
public class LoginFrame extends JFrame {

    // Service for user-related operations.
    private final UserService userService;
    // Service for car-related operations.
    private final CarService carService;

    // UI Components.
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    /**
     * Constructor for the LoginFrame.
     * @param userService The service for user operations.
     * @param carService The service for car operations.
     */
    public LoginFrame(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;

        initUI();
    }

    /**
     * Initializes and configures the UI components.
     */
    private void initUI() {
        setTitle("Login - Car Company");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when X is pressed.
        setLocationRelativeTo(null); // Center the window on the screen.

        // Create the main panel with a grid layout.
        // 3 rows, 2 columns, 10px spacing between them.
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        // Add a border (padding) around the panel.
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add the components (label + field).
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        panel.add(loginButton);

        registerButton = new JButton("Register");
        panel.add(registerButton);

        // Add the logic (actions) for the buttons.
        loginButton.addActionListener(e -> onLogin());
        registerButton.addActionListener(e -> onRegister());

        // Add the panel to the window.
        add(panel);
    }

    /**
     * The action triggered when the "Login" button is pressed.
     */
    private void onLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username and password cannot be empty.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Optional<User> userOptional = userService.login(username, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Open the dashboard corresponding to the role.
            if (user.getRole() == Role.ADMIN) {
                AdminDashboard adminDashboard = new AdminDashboard(carService);
                adminDashboard.setVisible(true);
            } else {
                UserDashboard userDashboard = new UserDashboard(carService);
                userDashboard.setVisible(true);
            }
            // Close the login window.
            this.dispose();
        } else {
            // Display an error message.
            JOptionPane.showMessageDialog(this,
                    "Incorrect username or password.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * The action triggered when the "Register" button is pressed.
     */
    private void onRegister() {
        // Open the registration window.
        RegisterFrame registerFrame = new RegisterFrame(this, userService);
        registerFrame.setVisible(true);
        // The application will wait for the RegisterFrame to be closed,
        // because it is "modal" (see RegisterFrame.java).
    }
}
