import javax.swing.*;
import java.awt.*;

/**
 * Fereastră (dialog) pentru înregistrarea unui cont nou.
 * Folosim JDialog pentru a fi "modal" - blochează interacțiunea
 * cu fereastra de login până când aceasta este închisă.
 */
public class RegisterFrame extends JDialog {

    private final UserService userService;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backButton; // Renamed from cancelButton

    public RegisterFrame(Frame owner, UserService userService) {
        super(owner, "Înregistrare Cont Nou", true); // "true" îl face modal
        this.userService = userService;

        initUI();
    }

    private void initUI() {
        setSize(400, 250);
        setLocationRelativeTo(getOwner()); // Se centrează relativ la fereastra de login

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Utilizator:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Parolă:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Confirmă Parola:"));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        registerButton = new JButton("Înregistrare");
        panel.add(registerButton);

        backButton = new JButton("Înapoi"); // Renamed from "Anulare"
        panel.add(backButton);

        // Acțiuni
        registerButton.addActionListener(e -> onRegister());
        backButton.addActionListener(e -> this.dispose()); // Închide fereastra curentă

        add(panel);
    }

    /**
     * Acțiunea de înregistrare.
     */
    private void onRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Câmpurile nu pot fi goale.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Parolele nu se potrivesc.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Încercăm să înregistrăm folosind serviciul
        boolean success = userService.register(username, password);

        if (success) {
            JOptionPane.showMessageDialog(this, "Cont creat cu succes! Vă puteți loga.", "Succes", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Închide fereastra de înregistrare
        } else {
            JOptionPane.showMessageDialog(this, "Numele de utilizator este deja folosit.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
