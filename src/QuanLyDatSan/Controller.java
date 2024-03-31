package QuanLyDatSan;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import QuanLySanCau.SuaFrm;

public class Controller {
    private Model model;
    private View view;
    private Connection conn;
    private Statement st;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // Thêm trình nghe cho nút Submit trên View
        this.view.addAddListener(new AddListener());
        // Thêm trình nghe cho nút Edit trên View
        this.view.addEditListener(new EditListener());
        // Thêm trình nghe cho nút Delete trên View
        this.view.addDeleteListener(new DeleteListener());
        // Thêm trình nghe cho nút Cancel trên View
        this.view.addCancelListener(new CancelListener());
        

        // Kết nối đến cơ sở dữ liệu khi tạo đối tượng Controller
        connectToDatabase();

        // Hiển thị dữ liệu từ cơ sở dữ liệu khi form được mở
        displayData();
    }

    // Phương thức này để kết nối đến cơ sở dữ liệu
    private void connectToDatabase() {
        try {
            // Thực hiện kết nối đến cơ sở dữ liệu
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
            String query = "SELECT * FROM danhsachdatsan"; // Thay đổi thành tên bảng của bạn
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // Lớp trình nghe cho nút Add
    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Tạo một instance của lớp AddForm mới
            AddFrm afrm = new AddFrm();
            // Hiển thị form mới
            afrm.setVisible(true);
            // Thực hiện các hành động khi nút Submit được nhấn (nếu cần)
            // Ví dụ: lấy dữ liệu từ các trường nhập liệu, thêm dữ liệu vào cơ sở dữ liệu, sau đó cập nhật bảng
            // String[] data = view.getDatsanInfo();
            // model.addDataToDatabase(data);
            // Sau khi thêm dữ liệu, reload lại bảng để hiển thị dữ liệu mới
            displayData();
        }
    }

    // Lớp trình nghe cho nút Edit
    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.dataTable.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    String maDSValue = view.dataTable.getValueAt(selectedRow, 0).toString();
                    String maKHValue = view.dataTable.getValueAt(selectedRow, 1).toString();
                    String maSanValue = view.dataTable.getValueAt(selectedRow, 2).toString();
                    String maDHValue = view.dataTable.getValueAt(selectedRow, 3).toString();
                    String loaiSanValue = view.dataTable.getValueAt(selectedRow, 4).toString();
                    
                    // Chuyển đổi từ String sang java.sql.Date
                    java.sql.Date ngayBatDauValue = java.sql.Date.valueOf(view.dataTable.getValueAt(selectedRow, 5).toString());
                    java.sql.Date ngayKetThucValue = java.sql.Date.valueOf(view.dataTable.getValueAt(selectedRow, 6).toString());
                    
                    // Chuyển đổi từ String sang java.sql.Time (nếu cần)
                    java.sql.Time gioBatDauValue = java.sql.Time.valueOf(view.dataTable.getValueAt(selectedRow, 7).toString());
                    java.sql.Time gioKetThucValue = java.sql.Time.valueOf(view.dataTable.getValueAt(selectedRow, 8).toString());

                    List<String> cacThuTrongTuanValue = new ArrayList<>();
                    for (int i = 9; i <= 15; i++) {
                        Object value = view.dataTable.getValueAt(selectedRow, i);
                        if (value instanceof Boolean && (boolean) value) {
                            cacThuTrongTuanValue.add("Thứ " + (i - 8));
                        } else {
                            // Xử lý khi giá trị không phải là true
                        }
                    }
                    // Khởi tạo form EditFrm với các giá trị đã chuyển đổi
                    EditFrm editFrm = new EditFrm(maDSValue, maKHValue, maSanValue, maDHValue, loaiSanValue, ngayBatDauValue, ngayKetThucValue, gioBatDauValue, gioKetThucValue, cacThuTrongTuanValue);
                    editFrm.initComponents();
                } catch (IllegalArgumentException ex) {
                    // Xử lý ngoại lệ nếu có
                    JOptionPane.showMessageDialog(view, "Định dạng ngày tháng không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một hàng để sửa đổi.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Phương thức để xóa dữ liệu từ cơ sở dữ liệu
    private void deleteData(String maDS) {
        try {
            String query = "DELETE FROM danhsachdatsan WHERE MaDS = '" + maDS + "'";
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(view, "Bản ghi đã được xóa thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa bản ghi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Lớp trình nghe cho nút Delete
    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.dataTable.getSelectedRow(); // Lấy chỉ mục của hàng đã chọn
            if (selectedRow != -1) { // Nếu có một hàng được chọn
                String maDS = (String) view.dataTable.getValueAt(selectedRow, 0); // Lấy MaDS từ hàng đã chọn
                int option = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa bản ghi này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    deleteData(maDS); // Xóa bản ghi từ cơ sở dữ liệu
                    displayData(); // Làm mới bảng
                }
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một bản ghi để xóa.", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Lớp trình nghe cho nút Cancel
    class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Thực hiện các hành động khi nút Cancel được nhấn (nếu cần)
        }
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

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        view.setVisible(true);// Hiển thị form
    }
}