import javax.swing.*;

public class MainApp {
    public static void main(String[] args){
        UserService userService = new UserService();
        CarService carService = new CarService();

        SwingUtilities.invokeLater(() ->{
            LoginFrame loginFrame = new LoginFrame(userService , carService);
            loginFrame.setVisible(true);
                });
    }
}
