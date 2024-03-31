package QuanLyDatSan;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class EditFrm1 extends JFrame {
    private JTextField txtMaKH, txtMaSan, txtMaDH;
    private JSpinner spNgayBatDau, spNgayKetThuc, spGioBatDau, spGioKetThuc;
    private SpinnerDateModel dateModelBatDau, dateModelKetThuc, timeModelBatDau, timeModelKetThuc;
    private JCheckBox cbThu2, cbThu3, cbThu4, cbThu5, cbThu6, cbThu7, cbChuNhat;
    private JComboBox<String> cmbLoaiSan, cmbTrangThai;
    private JLabel MaKH, MaSan, MaDH, LoaiSan, NgayBatDau, NgayKetThuc, GioBatDau, GioKetThuc, CacThuTrongTuan, TrangThai;
    private JButton btnSubmit, btnCancel;

    public EditFrm1(String maKHValue, String maSanValue, String maDHValue, String loaiSanValue, Date ngayBatDauValue, Date ngayKetThucValue, Date gioBatDauValue, Date gioKetThucValue, List<String> cacThuTrongTuanValue) {
        initComponents(); // Khởi tạo thành phần UI
        // Gán giá trị cho các thành phần UI
        txtMaKH.setText(maKHValue);
        txtMaSan.setText(maSanValue);
        txtMaDH.setText(maDHValue);
        cmbLoaiSan.setSelectedItem(loaiSanValue);
        spNgayBatDau.setValue(ngayBatDauValue);
        spNgayKetThuc.setValue(ngayKetThucValue);
        spGioBatDau.setValue(gioBatDauValue);
        spGioKetThuc.setValue(gioKetThucValue);

        // Đánh dấu các ô checkbox tương ứng với các ngày trong tuần
        for (String day : cacThuTrongTuanValue) {
            switch (day) {
                case "Thứ 2":
                    cbThu2.setSelected(true);
                    break;
                case "Thứ 3":
                    cbThu3.setSelected(true);
                    break;
                case "Thứ 4":
                    cbThu4.setSelected(true);
                    break;
                case "Thứ 5":
                    cbThu5.setSelected(true);
                    break;
                case "Thứ 6":
                    cbThu6.setSelected(true);
                    break;
                case "Thứ 7":
                    cbThu7.setSelected(true);
                    break;
                case "Chủ nhật":
                    cbChuNhat.setSelected(true);
                    break;
                default:
                    break;
            }
        }
        
        setVisible(true); // Hiển thị frame
    }

    public void initComponents() {
        setTitle("Sửa dữ liệu đã chọn");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);

        // Khởi tạo các thành phần UI
        MaKH = new JLabel("Mã khách hàng:");
        MaSan = new JLabel("Mã sân:");
        MaDH = new JLabel("Mã đặt hàng:");
        LoaiSan = new JLabel("Loại sân:");
        NgayBatDau = new JLabel("Ngày bắt đầu:");
        NgayKetThuc = new JLabel("Ngày kết thúc:");
        GioBatDau = new JLabel("Giờ bắt đầu:");
        GioKetThuc = new JLabel("Giờ kết thúc:");
        CacThuTrongTuan = new JLabel("Các thứ trong tuần:");
        TrangThai = new JLabel("Trạng thái");

        txtMaKH = new JTextField(10);
        txtMaSan = new JTextField(10);
        txtMaDH = new JTextField(10);

        cmbLoaiSan = new JComboBox<>(new String[] {"VIP", "SVIP", "SSVIP"});
        cbThu2 = new JCheckBox("Thứ 2");
        cbThu3 = new JCheckBox("Thứ 3");
        cbThu4 = new JCheckBox("Thứ 4");
        cbThu5 = new JCheckBox("Thứ 5");
        cbThu6 = new JCheckBox("Thứ 6");
        cbThu7 = new JCheckBox("Thứ 7");
        cbChuNhat = new JCheckBox("Chủ nhật");
        cmbTrangThai = new JComboBox<>(new String[] {"0", "1"});

        dateModelBatDau =  new SpinnerDateModel();
        dateModelKetThuc =  new SpinnerDateModel();
        spNgayBatDau = new JSpinner(dateModelBatDau);
        spNgayKetThuc = new JSpinner(dateModelKetThuc);
        spNgayBatDau.setEditor(new JSpinner.DateEditor(spNgayBatDau, "dd/MM/yyyy"));
        spNgayKetThuc.setEditor(new JSpinner.DateEditor(spNgayKetThuc, "dd/MM/yyyy"));
        timeModelBatDau = new SpinnerDateModel();
        timeModelKetThuc = new SpinnerDateModel();
        spGioBatDau = new JSpinner(timeModelBatDau);
        spGioKetThuc = new JSpinner(timeModelKetThuc);
        JSpinner.DateEditor gioBatDau = new JSpinner.DateEditor(spGioBatDau, "HH:mm");
        JSpinner.DateEditor gioKetThuc = new JSpinner.DateEditor(spGioKetThuc, "HH:mm");
        spGioBatDau.setEditor(gioBatDau);
        spGioKetThuc.setEditor(gioKetThuc);

        btnSubmit = new JButton("Xác nhận");
        btnCancel = new JButton("Hủy");

        setVisible(true);

        // Gán sự kiện cho nút "Submit"
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi phương thức để sửa dữ liệu trong cơ sở dữ liệu
                editDataInDatabase();
                // Đóng cửa sổ JFrame sau khi sửa dữ liệu thành công
                dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng cửa sổ JFrame
                dispose();
            }
        });
    }

    // Phương thức thêm dữ liệu vào cơ sở dữ liệu
    private void editDataInDatabase() {
        String maKHValue = txtMaKH.getText();
        String maSanValue = txtMaSan.getText();
        String maDHValue = txtMaDH.getText();
        String loaiSanValue = (String) cmbLoaiSan.getSelectedItem();

        Date ngayBatDauValue = (Date) spNgayBatDau.getValue();
        Date ngayKetThucValue = (Date) spNgayKetThuc.getValue();
        Date gioBatDauValue = (Date) spGioBatDau.getValue();
        Date gioKetThucValue = (Date) spGioKetThuc.getValue();

        List<String> cacThuTrongTuanValue = new ArrayList<>();
        if (cbThu2.isSelected()) {
            cacThuTrongTuanValue.add("Thứ 2");
        }
        if (cbThu3.isSelected()) {
            cacThuTrongTuanValue.add("Thứ 3");
        }
        if (cbThu4.isSelected()) {
            cacThuTrongTuanValue.add("Thứ 4");
        }
        if (cbThu5.isSelected()) {
            cacThuTrongTuanValue.add("Thứ 5");
        }
        if (cbThu6.isSelected()) {
            cacThuTrongTuanValue.add("Thứ 6");
        }
        if (cbThu7.isSelected()) {
            cacThuTrongTuanValue.add("Thứ 7");
        }
        if (cbChuNhat.isSelected()) {
            cacThuTrongTuanValue.add("Chủ nhật");
        }      

        String URL = "jdbc:mysql://localhost:3306/qlsancau";
        String USER = "root";
        String PASS = "";

        // Sử dụng try-with-resources để tự đóng Connection và PreparedStatement
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE danhsachdatsan SET MaKH=?, MaSan=?, MaDH=?, LoaiSan=?, NgayBatDau=?, NgayKetThuc=?, GioBatDau=?, GioKetThuc=?, Thu_2=?, Thu_3=?, Thu_4=?, Thu_5=?, Thu_6=?, Thu_7=?, ChuNhat=?, TrangThai=? WHERE MaDS=?";
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                // Đặt giá trị cho các tham số trong câu lệnh SQL
                st.setString(1, txtMaKH.getText());
                st.setString(2, txtMaSan.getText());
                st.setString(3, txtMaDH.getText());
                st.setString(4, cmbLoaiSan.getSelectedItem().toString());
                st.setDate(5, new java.sql.Date (spNgayBatDau.getValue()));
                st.setTimestamp(6, new Timestamp(((java.util.Date) spNgayKetThuc.getValue()).getTime()));
                st.setTimestamp(7, new Timestamp(((java.util.Date) spGioBatDau.getValue()).getTime()));
                st.setTimestamp(8, new Timestamp(((java.util.Date) spGioKetThuc.getValue()).getTime()));

                // Xử lý các ô checkbox và thiết lập giá trị cho tham số trong câu lệnh SQL
                st.setString(9, cbThu2.isSelected() ? "1" : "0");
                st.setString(10, cbThu3.isSelected() ? "1" : "0");
                st.setString(11, cbThu4.isSelected() ? "1" : "0");
                st.setString(12, cbThu5.isSelected() ? "1" : "0");
                st.setString(13, cbThu6.isSelected() ? "1" : "0");
                st.setString(14, cbThu7.isSelected() ? "1" : "0");
                st.setString(15, cbChuNhat.isSelected() ? "1" : "0");

                st.setString(16, cmbTrangThai.getSelectedItem().toString());

                // Đặt giá trị cho tham số MaDS
                st.setString(17, txtMaDH.getText());

                int rowsUpdated = st.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Dữ liệu đã được sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không có bản ghi nào được sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }    
    }
}
