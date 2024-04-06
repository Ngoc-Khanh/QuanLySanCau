package view;

import java.sql.*;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import controller.DatSanController;
import controller.DatSanController.AddFormListener;

public class DatSanAddView extends JFrame {
    private JTextField txtMaKH, txtMaSan, txtTongTienSan;
    private JSpinner spNgayBatDau, spNgayKetThuc, spGioBatDau, spGioKetThuc;
    private SpinnerDateModel dateModelBatDau, dateModelKetThuc, timeModelBatDau, timeModelKetThuc;
    private JCheckBox cbThu2, cbThu3, cbThu4, cbThu5, cbThu6, cbThu7, cbChuNhat;
    private JComboBox<String> cmbLoaiSan;
    private JLabel MaKH, MaSan, LoaiSan, NgayBatDau, NgayKetThuc,
            GioBatDau, GioKetThuc, TongTienSan, CacThuTrongTuan;
    private JButton btnSubmit, btnCancel;
    private AddFormListener addFormListener;

    public void setAddFormListener(AddFormListener listener) {
        this.addFormListener = listener;
    }

    public DatSanAddView() {
        setTitle("Thêm dữ liệu mới");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);

        MaKH = new JLabel("Mã khách hàng:");
        MaSan = new JLabel("Mã sân:");
        LoaiSan = new JLabel("Loại sân:");
        NgayBatDau = new JLabel("Ngày bắt đầu:");
        NgayKetThuc = new JLabel("Ngày kết thúc:");
        GioBatDau = new JLabel("Giờ bắt đầu:");
        GioKetThuc = new JLabel("Giờ kết thúc:");
        CacThuTrongTuan = new JLabel("Các thứ trong tuần:");
        TongTienSan = new JLabel("Tổng tiền sân");

        txtMaKH = new JTextField(10);
        txtMaSan = new JTextField(10);

        cmbLoaiSan = new JComboBox<>(new String[] { "VIP", "SVIP", "SSVIP" });
        cbThu2 = new JCheckBox("Thứ 2");
        cbThu3 = new JCheckBox("Thứ 3");
        cbThu4 = new JCheckBox("Thứ 4");
        cbThu5 = new JCheckBox("Thứ 5");
        cbThu6 = new JCheckBox("Thứ 6");
        cbThu7 = new JCheckBox("Thứ 7");
        cbChuNhat = new JCheckBox("Chủ nhật");

        txtTongTienSan = new JTextField(10);
        txtTongTienSan.setEditable(false);

        dateModelBatDau = new SpinnerDateModel();
        dateModelBatDau.setCalendarField(Calendar.DAY_OF_MONTH);
        spNgayBatDau = new JSpinner(dateModelBatDau);
        spNgayBatDau.setEditor(new JSpinner.DateEditor(spNgayBatDau, "dd/MM/yyyy"));

        dateModelKetThuc = new SpinnerDateModel();
        dateModelKetThuc.setCalendarField(Calendar.DAY_OF_MONTH);
        spNgayKetThuc = new JSpinner(dateModelKetThuc);
        spNgayKetThuc.setEditor(new JSpinner.DateEditor(spNgayKetThuc, "dd/MM/yyyy"));

        timeModelBatDau = new SpinnerDateModel();
        timeModelBatDau.setCalendarField(Calendar.MINUTE);
        spGioBatDau = new JSpinner(timeModelBatDau);
        JSpinner.DateEditor gioBatDau = new JSpinner.DateEditor(spGioBatDau, "HH:mm");
        spGioBatDau.setEditor(gioBatDau);

        timeModelKetThuc = new SpinnerDateModel();
        timeModelKetThuc.setCalendarField(Calendar.MINUTE);
        spGioKetThuc = new JSpinner(timeModelKetThuc);
        JSpinner.DateEditor gioKetThuc = new JSpinner.DateEditor(spGioKetThuc, "HH:mm");
        spGioKetThuc.setEditor(gioKetThuc);

        btnSubmit = new JButton("Xác nhận");
        btnCancel = new JButton("Hủy");

        panel.setLayout(new GridLayout(0, 2));
        panel.add(MaKH);
        panel.add(txtMaKH);
        panel.add(MaSan);
        panel.add(txtMaSan);
        panel.add(LoaiSan);
        panel.add(cmbLoaiSan);
        panel.add(NgayBatDau);
        panel.add(spNgayBatDau);
        panel.add(NgayKetThuc);
        panel.add(spNgayKetThuc);
        panel.add(GioBatDau);
        panel.add(spGioBatDau);
        panel.add(GioKetThuc);
        panel.add(spGioKetThuc);
        panel.add(CacThuTrongTuan);
        panel.add(cbThu2);
        panel.add(cbThu3);
        panel.add(cbThu4);
        panel.add(cbThu5);
        panel.add(cbThu6);
        panel.add(cbThu7);
        panel.add(cbChuNhat);
        // panel.add(TongTienSan);
        // panel.add(txtTongTienSan);

        panel.add(btnCancel);
        panel.add(btnSubmit);

        setVisible(true);

        // Gán sự kiện cho nút "Submit"
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị hộp thoại xác nhận
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm dữ liệu này?", "Xác nhận",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    // Nếu người dùng chọn "Yes" trong hộp thoại xác nhận
                    addDataToDatabase();
                }
            }
        });

        // Gán sự kiện cho nút "Cancel"
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng cửa sổ JFrame
                dispose();
            }
        });
    }

    // Phương thức thêm dữ liệu vào cơ sở dữ liệu
    private void addDataToDatabase() {
        // Kết nối tới cơ sở dữ liệu
        String URL = "jdbc:mysql://localhost:3306/sancau";
        String USER = "root";
        String PASS = "";

        // Chuẩn bị câu lệnh SQL để thêm dữ liệu vào bảng
        String querry = "INSERT INTO danhsachdatsan (MaKH, MaSan, LoaiSan, NgayBatDau, NgayKetThuc, GioBatDau, GioKetThuc, Thu_2, Thu_3, Thu_4, Thu_5, Thu_6, Thu_7, ChuNhat, SoGioThue) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement st = conn.prepareStatement(querry);

            // Đặt giá trị cho các tham số trong câu lệnh SQL
            st.setString(1, txtMaKH.getText());
            st.setString(2, txtMaSan.getText());
            st.setString(3, cmbLoaiSan.getSelectedItem().toString());
            st.setTimestamp(4, new Timestamp(((java.util.Date) spNgayBatDau.getValue()).getTime()));
            st.setTimestamp(5, new Timestamp(((java.util.Date) spNgayKetThuc.getValue()).getTime()));
            st.setObject(6, spGioBatDau.getValue());
            st.setObject(7, spGioKetThuc.getValue());

            // Xử lý các ô checkbox và thiết lập giá trị cho tham số trong câu lệnh SQL
            st.setString(8, cbThu2.isSelected() ? "1" : "0");
            st.setString(9, cbThu3.isSelected() ? "1" : "0");
            st.setString(10, cbThu4.isSelected() ? "1" : "0");
            st.setString(11, cbThu5.isSelected() ? "1" : "0");
            st.setString(12, cbThu6.isSelected() ? "1" : "0");
            st.setString(13, cbThu7.isSelected() ? "1" : "0");
            st.setString(14, cbChuNhat.isSelected() ? "1" : "0");

            // Tính số giờ nếu giờ kết thúc lớn hơn giờ bắt đầu
            long milliseconds = ((java.util.Date) spGioKetThuc.getValue()).getTime()
                    - ((java.util.Date) spGioBatDau.getValue()).getTime();
            if (milliseconds >= 0) {
                int hours = (int) (milliseconds / (1000 * 60 * 60));
                st.setInt(15, hours);
            } else {
                // Nếu giờ kết thúc nhỏ hơn giờ bắt đầu, gán số giờ thuê là 0
                st.setInt(15, 0);
                JOptionPane.showMessageDialog(null, "Giờ kết thúc không thể nhỏ hơn giờ bắt đầu");
            }

            // st.setString(16, txtMaSan.getText());
            int rowsInserted = st.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công");
                addFormListener.onAddSuccess();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    }
}