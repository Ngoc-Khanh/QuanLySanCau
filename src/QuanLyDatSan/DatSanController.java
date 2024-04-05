package QuanLyDatSan;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import view.PhieuDatHangview;

public class DatSanController {
    private DatSanModel model;
    private DatSanView view;
    private Connection conn;
    private Statement st;

    public DatSanController(DatSanModel model, DatSanView view) {
        this.model = model;
        this.view = view;

        // Thêm trình nghe cho nút Submit trên View
        this.view.addAddListener(new AddListener());
        // Thêm trình nghe cho nút Edit trên View
        this.view.addEditListener(new EditListener());
        // Thêm trình nghe cho nút Save trên View
        this.view.addSaveListener(new SaveListener());
        // Thêm trình nghe cho nút Delete trên View
        this.view.addDeleteListener(new DeleteListener());
        // Thêm trình nghe cho nút Cancel trên View
        this.view.addCancelListener(new CancelListener());
        // Thêm trình nghe cho nút Excel trên view
        this.view.addExcelListener(new ExcelListener());
        // Thêm trình nghe cho nút PHiếu đơn hàng trên view
        this.view.addPhieuDonHangListener(new PhieuDonHangListener());



        // Kết nối đến cơ sở dữ liệu khi tạo đối tượng Controller
        connectToDatabase();

        // Hiển thị dữ liệu từ cơ sở dữ liệu khi form được mở
        displayData();
    }

    // Phương thức này để kết nối đến cơ sở dữ liệu
    private void connectToDatabase() {
        try {
            // Thực hiện kết nối đến cơ sở dữ liệu
            String URL = "jdbc:mysql://localhost:3306/quynh";
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

    public interface AddFormListener {
        void onAddSuccess();
    }

    // Lớp trình nghe cho nút Add
    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Tạo một instance của lớp AddForm mới
            DatSanAddFrm afrm = new DatSanAddFrm();
            // Đăng ký DatSanController làm listener
            afrm.setAddFormListener(new AddFormListener() {
                @Override
                public void onAddSuccess() {
                    // Khi thêm thành công, reload lại bảng
                    displayData();
                }
            });
            // Hiển thị form mới
            afrm.setVisible(true);
        }
    }

    private void lockFields() {
        view.txtMaKH.setEditable(false);
        view.txtMaSan.setEditable(false);
        view.txtMaDH.setEditable(false);
        view.cmbLoaiSan.setEnabled(false);
        view.spNgayBatDau.setEnabled(false);
        view.spNgayKetThuc.setEnabled(false);
        view.spGioBatDau.setEnabled(false);
        view.spGioKetThuc.setEnabled(false);
        view.cmbTrangThai.setEnabled(false);
        view.cbThu2.setEnabled(false);
        view.cbThu3.setEnabled(false);
        view.cbThu4.setEnabled(false);
        view.cbThu5.setEnabled(false);
        view.cbThu6.setEnabled(false);
        view.cbThu7.setEnabled(false);
        view.cbChuNhat.setEnabled(false);

        view.btnAdd.setEnabled(true);
        view.btnEdit.setEnabled(true);
        view.btnDelete.setEnabled(true);
        view.btnSave.setEnabled(false);
        view.btnCancel.setEnabled(false);
        view.btnExcel.setEnabled(true);
    }

    private void unlockFields() {
        view.txtMaKH.setEditable(true);
        view.txtMaSan.setEditable(true);
        view.txtMaDH.setEditable(true);
        view.cmbLoaiSan.setEnabled(true);
        view.spNgayBatDau.setEnabled(true);
        view.spNgayKetThuc.setEnabled(true);
        view.spGioBatDau.setEnabled(true);
        view.spGioKetThuc.setEnabled(true);
        view.cmbTrangThai.setEnabled(true);
        view.cbThu2.setEnabled(true);
        view.cbThu3.setEnabled(true);
        view.cbThu4.setEnabled(true);
        view.cbThu5.setEnabled(true);
        view.cbThu6.setEnabled(true);
        view.cbThu7.setEnabled(true);
        view.cbChuNhat.setEnabled(true);

        view.btnAdd.setEnabled(false);
        view.btnEdit.setEnabled(false);
        view.btnDelete.setEnabled(false);
        view.btnSave.setEnabled(true);
        view.btnCancel.setEnabled(true);
        view.btnExcel.setEnabled(false);
    }

    private void editData(String maDS) {
        unlockFields();
    }

    private void fillFields(int selectedRow) {
        if (selectedRow != -1) {
            view.txtMaKH.setText((String) view.dataTable.getValueAt(selectedRow, 1));
            view.txtMaSan.setText((String) view.dataTable.getValueAt(selectedRow, 2));
            view.txtMaDH.setText((String) view.dataTable.getValueAt(selectedRow, 3));
            view.cmbLoaiSan.setSelectedItem(view.dataTable.getValueAt(selectedRow, 4));
            view.spNgayBatDau.setValue(view.dataTable.getValueAt(selectedRow, 5));
            view.spNgayKetThuc.setValue(view.dataTable.getValueAt(selectedRow, 6));
            view.spGioBatDau.setValue(view.dataTable.getValueAt(selectedRow, 7));
            view.spGioKetThuc.setValue(view.dataTable.getValueAt(selectedRow, 8));
            view.cbThu2.setSelected(Boolean.parseBoolean((String) view.dataTable.getValueAt(selectedRow, 9)));
            view.cbThu3.setSelected(Boolean.parseBoolean((String) view.dataTable.getValueAt(selectedRow, 10)));
            view.cbThu4.setSelected(Boolean.parseBoolean((String) view.dataTable.getValueAt(selectedRow, 11)));
            view.cbThu5.setSelected(Boolean.parseBoolean((String) view.dataTable.getValueAt(selectedRow, 12)));
            view.cbThu6.setSelected(Boolean.parseBoolean((String) view.dataTable.getValueAt(selectedRow, 13)));
            view.cbThu7.setSelected(Boolean.parseBoolean((String) view.dataTable.getValueAt(selectedRow, 14)));
            view.cbChuNhat.setSelected(Boolean.parseBoolean((String) view.dataTable.getValueAt(selectedRow, 15)));
        }
    }

    // Lớp trình nghe cho nút Edit
    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.dataTable.getSelectedRow();
            if (selectedRow != -1) {
                String maDS = (String) view.dataTable.getValueAt(selectedRow, 0);
                editData(maDS);
                fillFields(selectedRow);
                displayData();
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một bản ghi để sửa.", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveData(String maDS) {
        String query = "UPDATE danhsachdatsan SET MaKH = ?, MaSan = ?, MaDH = ?, LoaiSan = ?, NgayBatDau = ?, NgayKetThuc = ?, GioBatDau = ?, GioKetThuc = ?, Thu_2 = ?, Thu_3 = ?, Thu_4 = ?, Thu_5 = ?, Thu_6 = ?, Thu_7 = ?, ChuNhat = ?, SoGioThue = ? WHERE MaDS = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, view.txtMaKH.getText());
            st.setString(2, view.txtMaSan.getText());
            st.setString(3, view.txtMaDH.getText());
            st.setString(4, view.cmbLoaiSan.getSelectedItem().toString());
            st.setTimestamp(5, new Timestamp(((java.util.Date) view.spNgayBatDau.getValue()).getTime()));
            st.setTimestamp(6, new Timestamp(((java.util.Date) view.spNgayKetThuc.getValue()).getTime()));
            st.setObject(7, view.spGioBatDau.getValue());
            st.setObject(8, view.spGioKetThuc.getValue());
            st.setString(9, view.cbThu2.isSelected() ? "1" : "0");
            st.setString(10, view.cbThu3.isSelected() ? "1" : "0");
            st.setString(11, view.cbThu4.isSelected() ? "1" : "0");
            st.setString(12, view.cbThu5.isSelected() ? "1" : "0");
            st.setString(13, view.cbThu6.isSelected() ? "1" : "0");
            st.setString(14, view.cbThu7.isSelected() ? "1" : "0");
            st.setString(15, view.cbChuNhat.isSelected() ? "1" : "0");

            // Tính số giờ
            long milliseconds = ((java.util.Date) view.spGioKetThuc.getValue()).getTime()
                    - ((java.util.Date) view.spGioBatDau.getValue()).getTime();
            int hours = (int) (milliseconds / (1000 * 60 * 60));
            st.setInt(16, hours);

            st.setString(17, maDS);
            st.executeUpdate();
            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công");
                lockFields();
            } else {
                JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    }

    class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.dataTable.getSelectedRow();
            if (selectedRow != -1) {
                String maDS = (String) view.dataTable.getValueAt(selectedRow, 0);
                saveData(maDS);
                displayData();
            } else {
                JOptionPane.showMessageDialog(view, "Lựa chọn hàng cần sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Phương thức để xóa dữ liệu từ cơ sở dữ liệu
    private void deleteData(String maDS) {
        try {
            String query = "DELETE FROM danhsachdatsan WHERE MaDS = '" + maDS + "'";
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(view, "Bản ghi đã được xóa thành công.", "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa bản ghi: " + ex.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lớp trình nghe cho nút Delete
    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.dataTable.getSelectedRow(); // Lấy chỉ mục của hàng đã chọn
            if (selectedRow != -1) { // Nếu có một hàng được chọn
                String maDS = (String) view.dataTable.getValueAt(selectedRow, 0); // Lấy MaDS từ hàng đã chọn
                int option = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa bản ghi này không?",
                        "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    deleteData(maDS); // Xóa bản ghi từ cơ sở dữ liệu
                    displayData(); // Làm mới bảng
                }
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một bản ghi để xóa.", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Lớp trình nghe cho nút Excel
    class ExcelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sach dat hang");
            XSSFRow row = null;
            Cell cell = null;

            row = sheet.createRow(0);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã đặt sân");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã khách hàng");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã sân");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Mã đặt hàng");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Loại sân");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày bắt đầu");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Ngày kết thúc");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Giờ bắt đầu");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Giờ kết thúc");

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Số giờ thuê");

            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Thứ 2");

            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("Thứ 3");

            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("Thứ 4");

            cell = row.createCell(13, CellType.STRING);
            cell.setCellValue("Thứ 5");

            cell = row.createCell(14, CellType.STRING);
            cell.setCellValue("Thứ 6");

            cell = row.createCell(15, CellType.STRING);
            cell.setCellValue("Thứ 7");

            cell = row.createCell(16, CellType.STRING);
            cell.setCellValue("Chủ nhật");

            cell = row.createCell(17, CellType.STRING);
            cell.setCellValue("Trạng thái");

            int rowCount = view.dataTable.getRowCount(); // Lấy số lượng hàng trên bảng
            int columnCount = view.dataTable.getColumnCount(); // Lấy số lượng cột trên bảng

            // Lặp qua từng hàng của bảng
            for (int i = 0; i < rowCount; i++) {
                row = sheet.createRow(i + 1); // Bắt đầu từ hàng 1, vì hàng 0 đã được sử dụng cho tiêu đề

                // Lặp qua từng cột của hàng và thêm dữ liệu vào file Excel
                for (int j = 0; j < columnCount; j++) {
                    Object value = view.dataTable.getValueAt(i, j); // Lấy giá trị của ô tại hàng i, cột j
                    if (value != null) {
                        cell = row.createCell(j, CellType.STRING);
                        cell.setCellValue(value.toString());
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream("D://danhsachdatsan.xlsx")) {
                workbook.write(fos);
                JOptionPane.showMessageDialog(null, "Excel Success!.File path: D://danhsachdatsan.xlsx");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while exporting to Excel.");
            }
        }
    }

    // Lớp trình nghe cho nút Cancel
    class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn hủy không?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                lockFields(); // Mở khóa các TextField và checkbox trước khi thoát
            }
        }
    }

    // Lớp trình nghe cho nút Phiếu đơn hàng
    class PhieuDonHangListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.dataTable.getSelectedRow();
            if (selectedRow != -1) {
                String maDS = (String) view.dataTable.getValueAt(selectedRow, 0);
                new PhieuDatHangview();
                displayData();
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một bản ghi để sửa.", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
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
        DatSanView view = new DatSanView();
        DatSanModel model = new DatSanModel();
        DatSanController controller = new DatSanController(model, view);
        view.setVisible(true);
    }
}