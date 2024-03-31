/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLySanCau;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Krug
 */
public class ThemFrm extends JFrame{
    private static JTextField txtMaKH, txtMaDH;
    private static JSpinner spNgayBatDau, spNgayKetThuc, spGioBatDau, spGioKetThuc;
    private static SpinnerDateModel dateModelBatDau, dateModelKetThuc ,timeModelBatDau, timeModelKetThuc;
    private static JCheckBox cbThu2, cbThu3, cbThu4, cbThu5, cbThu6, cbThu7, cbChuNhat;
    private static JComboBox<String> cmbLoaiSan;
    private static JLabel maKH, maDH, loaiSan, ngayBatDau, ngayKetThuc, gioBatDau, gioKetThuc, cacThuTrongTuan;
    private static JButton btnXacNhan, btnHuy;
    private static boolean isClosed = true;
    
    public static void Them() {
        // Tạo một JFrame mới
        JFrame frame = new JFrame("Thêm mới dữ liệu");
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Thêm các thành phần UI cần thiết vào frame
        JPanel panel = new JPanel();
        frame.add(panel);
       
        maKH = new JLabel("Mã khách hàng");
        maDH = new JLabel("Mã đặt hàng");
        loaiSan = new JLabel("Loại sân");
        ngayBatDau = new JLabel("Ngày bắt đầu");
        ngayKetThuc = new JLabel("Ngày kết thúc");
        gioBatDau = new JLabel("Giờ bắt đầu");
        gioKetThuc = new JLabel("Giờ kết thúc");
        cacThuTrongTuan = new JLabel("Các thứ trong tuần");

        txtMaKH = new JTextField(10);
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
                // Lấy dữ liệu từ các trường nhập liệu
                String maKHValue = txtMaKH.getText();
                String maDHValue = txtMaDH.getText();
                String loaiSanValue = (String) cmbLoaiSan.getSelectedItem();
                // Lấy dữ liệu ngày và giờ bắt đầu và kết thúc
                Date ngayBatDauValue = (Date) spNgayBatDau.getValue();
                Date ngayKetThucValue = (Date) spNgayKetThuc.getValue();
                Date gioBatDauValue = (Date) spGioBatDau.getValue();
                Date gioKetThucValue = (Date) spGioKetThuc.getValue();
                // Xử lý các ô checkbox về thứ trong tuần
                // Lưu ý: Bạn cần kiểm tra trạng thái của các ô checkbox và thêm vào danh sách các ngày được chọn
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
                
                try {
                    Connection conn = ConnectDB.getConnection();
                    // Chuẩn bị câu lệnh SQL để thêm dữ liệu vào bảng
                    String sql = "INSERT INTO danhsachdatsan (MaKH, LoaiSan, NgayBatDau, NgayKetThuc, GioBatDau, GioKetThuc, Thu_2, Thu_3, Thu_4, Thu_5, Thu_6, Thu_7, ChuNhat) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement st = conn.prepareStatement(sql);
                    // Đặt giá trị cho các tham số trong câu lệnh SQL
                    st.setString(1, maKHValue);
//                    st.setString(2, maDHValue);
                    st.setString(2, loaiSanValue);
                    st.setDate(3, new java.sql.Date(ngayBatDauValue.getTime()));
                    st.setDate(4, new java.sql.Date(ngayKetThucValue.getTime()));
                    st.setTime(5, new java.sql.Time(gioBatDauValue.getTime()));
                    st.setTime(6, new java.sql.Time(gioKetThucValue.getTime()));
                    // Chuyển danh sách các ngày được chọn thành một chuỗi và đặt vào câu lệnh SQL
                    String[] daysOfWeek = {"Thu_2", "Thu_3", "Thu_4", "Thu_5", "Thu_6", "Thu_7", "ChuNhat"};
                        for (int i = 0; i < 7; i++) {
                            if (cacThuTrongTuanValue.contains("Thứ " + (i + 2))) {
                                st.setBoolean(7 + i, true);
                            } else {
                                st.setBoolean(7 + i, false);
                            }
                        }
                    // Thực thi câu lệnh SQL để thêm dữ liệu vào cơ sở dữ liệu
                    int rowsInserted = st.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Thêm dữ liệu thành công!");
                    }
//                    SanCauFrm frm = new SanCauFrm();
//                    frm.refreshTable();
                    // Đóng kết nối và các tài nguyên liên quan
                    st.close();
                    conn.close();
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
