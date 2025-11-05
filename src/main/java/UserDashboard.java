import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {
    private final CarService carService;
    private JList<Car> carList;
    private DefaultListModel<Car> listModel;
    private JButton logoutButton;
    private JButton backButton;


    public UserDashboard(CarService carService) {
        this.carService = carService;
        initUI();
        loadCars();
    }

    private void initUI() {
        setTitle("Panou Utilizator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10 , 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panoul de sus care va conține și butoanele și titlul
        JPanel topSectionPanel = new JPanel(new BorderLayout());

        // Panoul pentru butoane, aliniat la dreapta
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutButton = new JButton("Deconectare");
        backButton = new JButton("Înapoi");
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);

        // Adăugăm panoul cu butoane în partea de sus a secțiunii de sus
        topSectionPanel.add(buttonPanel, BorderLayout.NORTH);

        // Adăugăm eticheta sub panoul cu butoane
        JLabel titleLabel = new JLabel("Lista de Masini:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topSectionPanel.add(titleLabel, BorderLayout.CENTER);

        // Adăugăm întreaga secțiune de sus la panoul principal
        mainPanel.add(topSectionPanel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        carList = new JList<>(listModel);
        carList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(carList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        logoutButton.addActionListener(e -> onLogout());
        backButton.addActionListener(e -> onLogout());
    }

    private void loadCars() {
        listModel.clear();
        carService.getCars().forEach(listModel::addElement);
    }

    private void onLogout() {
        this.dispose();
        new LoginFrame(new UserService(), carService).setVisible(true);
    }

}
