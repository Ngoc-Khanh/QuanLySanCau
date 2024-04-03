package MainMenu;

import java.sql.*;

public class MainMenuController {
    private MainMenuModel model;
    private MainMenuView view;
    private Connection conn;
    private Statement st;

    public MainMenuController(MainMenuModel model, MainMenuView view) {
        this.model = model;
        this.view = view;

    }

    private void connectToDatabase() {
        try {
            String URL = "jdbc:mysql://localhost:3306/qlsancau";
            String USER = "root";
            String PASS = "";
            conn = DriverManager.getConnection(URL, USER, PASS);
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainMenuModel model = new MainMenuModel();
        MainMenuView view = new MainMenuView();
        MainMenuController controller = new MainMenuController(model, view);
        view.setVisible(true);
    }
}
