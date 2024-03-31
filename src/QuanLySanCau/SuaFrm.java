/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLySanCau;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Krug
 */
public class SuaFrm {
    private static JFrame frame;
    private static JTextField txtMaDS, txtMaKH, txtMaSan, txtMaDH;
    private static JSpinner spNgayBatDau, spNgayKetThuc, spGioBatDau, spGioKetThuc;
    private static SpinnerDateModel dateModelBatDau, dateModelKetThuc ,timeModelBatDau, timeModelKetThuc;
    private static JCheckBox cbThu2, cbThu3, cbThu4, cbThu5, cbThu6, cbThu7, cbChuNhat;
    private static JComboBox<String> cmbLoaiSan;
    private static JLabel maDS, maKH, maSan, maDH, loaiSan, ngayBatDau, ngayKetThuc, gioBatDau, gioKetThuc, cacThuTrongTuan;
    private static JButton btnXacNhan, btnHuy;
    
    public SuaFrm(String maDSValue, String maKHValue, String maSanValue, String maDHValue,
                  String loaiSanValue, Date ngayBatDauValue, Date ngayKetThucValue,
                  Date gioBatDauValue, Date gioKetThucValue, List<String> cacThuTrongTuanValue) {
        // Khởi tạo các thành phần UI trước khi sử dụng chúng
        if (frame == null) {
            initComponents(); // Khởi tạo thành phần UI nếu chưa được khởi tạo
        }
        
        // Gán giá trị cho các thành phần UI
        txtMaDS.setText(maDSValue);
        txtMaKH.setText(maKHValue);
        txtMaSan.setText(maSanValue);
        txtMaDH.setText(maDHValue);
        cmbLoaiSan.setSelectedItem(loaiSanValue);
        spNgayBatDau.setValue(ngayBatDauValue);
        spNgayKetThuc.setValue(ngayKetThucValue);
        spGioBatDau.setValue(gioBatDauValue);
        spGioKetThuc.setValue(gioKetThucValue);
        // Vòng lặp để đánh dấu các ô checkbox tương ứng với các ngày trong tuần
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
        
        frame.setVisible(true);
    }
    
    public static void initComponents() {
        // Tạo một JFrame mới
        frame = new JFrame("Sửa thông tin đặt sân");
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        // Thêm các thành phần UI cần thiết vào frame
        JPanel panel = new JPanel();
        frame.add(panel);
       
        maDS = new JLabel("Mã đặt sân");    
        maKH = new JLabel("Mã khách hàng");
        maSan = new JLabel("Mã sân");
        maDH = new JLabel("Mã đặt hàng");
        loaiSan = new JLabel("Loại sân");
        ngayBatDau = new JLabel("Ngày bắt đầu");
        ngayKetThuc = new JLabel("Ngày kết thúc");
        gioBatDau = new JLabel("Giờ bắt đầu");
        gioKetThuc = new JLabel("Giờ kết thúc");
        cacThuTrongTuan = new JLabel("Các thứ trong tuần");

        txtMaDS= new JTextField(10);
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
        JSpinner.DateEditor GioBatDau = new JSpinner.DateEditor(spGioBatDau, "HH:mm");
        JSpinner.DateEditor GioKetThuc = new JSpinner.DateEditor(spGioKetThuc, "HH:mm");
        spGioBatDau.setEditor(GioBatDau);
        spGioKetThuc.setEditor(GioKetThuc);
        
        btnXacNhan = new JButton("Xác nhận");
        btnHuy = new JButton("Hủy");
        
        panel.setLayout(new GridLayout(0, 2));
        panel.add(maDS);
        panel.add(txtMaDS);
        panel.add(maSan);
        panel.add(txtMaSan);
        panel.add(maKH);
        panel.add(txtMaKH);
        panel.add(maDH);
        panel.add(txtMaDH);
        panel.add(loaiSan);
        panel.add(cmbLoaiSan);
        panel.add(ngayBatDau);
        panel.add(spNgayBatDau);
        panel.add(ngayKetThuc);
        panel.add(spNgayKetThuc);
        panel.add(gioBatDau);
        panel.add(spGioBatDau);
        panel.add(gioKetThuc);
        panel.add(spGioKetThuc);        
        panel.add(cacThuTrongTuan);
        panel.add(cbThu2);
        panel.add(cbThu3);
        panel.add(cbThu4);
        panel.add(cbThu5);
        panel.add(cbThu6);
        panel.add(cbThu7);
        panel.add(cbChuNhat);
        
        panel.add(btnHuy);
        panel.add(btnXacNhan);
        
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maDSValue = txtMaDS.getText();
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

                try (Connection conn = ConnectDB.getConnection()) {
                    String sql = "UPDATE danhsachdatsan SET MaKH=?, MaSan=?, MaDH=?, LoaiSan=?, NgayBatDau=?, NgayKetThuc=?, GioBatDau=?, GioKetThuc=?, Thu_2=?, Thu_3=?, Thu_4=?, Thu_5=?, Thu_6=?, Thu_7=?, ChuNhat=? WHERE MaDS=?";
                    try (PreparedStatement st = conn.prepareStatement(sql)) {
                        st.setString(1, maKHValue);
                        st.setString(2, maSanValue);
                        st.setString(3, maDHValue);
                        st.setString(4, loaiSanValue);
                        st.setDate(5, new java.sql.Date(ngayBatDauValue.getTime()));
                        st.setDate(6, new java.sql.Date(ngayKetThucValue.getTime()));
                        st.setTime(7, new java.sql.Time(gioBatDauValue.getTime()));
                        st.setTime(8, new java.sql.Time(gioKetThucValue.getTime()));

                        String[] daysOfWeek = {"Thu_2", "Thu_3", "Thu_4", "Thu_5", "Thu_6", "Thu_7", "ChuNhat"};
                        for (int i = 0; i < 7; i++) {
                            if (cacThuTrongTuanValue.contains("Thứ " + (i + 2))) {
                                st.setBoolean(9 + i, true);
                            } else {
                                st.setBoolean(9 + i, false);
                            }
                        }

                        st.setString(16, maDSValue);

                        int rowsUpdated = st.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Sửa dữ liệu thành công!");
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Lỗi: " + ex.getMessage());
                }
            }
        });
        
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng cửa sổ JFrame
                frame.dispose();
            }
        });
    }
}
