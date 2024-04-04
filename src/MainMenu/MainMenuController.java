package MainMenu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // Phương thức này để hiển thị dữ liệu từ cơ sở dữ liệu lên bảng
    private void displayData() {
        ResultSet resultSet = getDataFromDatabase();
        try {
            // Xử lý resultSet để lấy dữ liệu từ cơ sở dữ liệu
            String[][] data = processDataFromResultSet(resultSet);

            // Gọi phương thức updateTableData của View để cập nhật dữ liệu lên bảng
            view.updateTableData(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức này để thực hiện truy vấn dữ liệu từ cơ sở dữ liệu
    private ResultSet getDataFromDatabase() {
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM danhsachdatsan";
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // Phương thức này để xử lý dữ liệu từ ResultSet và chuyển đổi thành mảng 2D
    private String[][] processDataFromResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String[]> dataList = new ArrayList<>();

        // Duyệt qua từng dòng của ResultSet
        while (resultSet.next()) {
            String[] row = new String[columnCount];
            // Lấy dữ liệu từng cột trong dòng hiện tại
            for (int i = 0; i < columnCount; i++) {
                row[i] = resultSet.getString(i + 1); // Lấy dữ liệu từ cột i (index bắt đầu từ 1)
            }
            dataList.add(row); // Thêm dòng vào danh sách dữ liệu
        }

        // Chuyển danh sách dữ liệu từ List<String[]> thành mảng 2D
        String[][] data = new String[dataList.size()][];
        dataList.toArray(data);

        return data;
    }
}
