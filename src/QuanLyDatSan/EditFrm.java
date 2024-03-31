package QuanLyDatSan;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class EditFrm {
    private JFrame frame;
    private JTextField txtMaDS, txtMaKH, txtMaSan, txtMaDH;
    private JSpinner spNgayBatDau, spNgayKetThuc, spGioBatDau, spGioKetThuc;
    private SpinnerDateModel dateModelBatDau, dateModelKetThuc, timeModelBatDau, timeModelKetThuc;
    private JCheckBox cbThu2, cbThu3, cbThu4, cbThu5, cbThu6, cbThu7, cbChuNhat;
    private JComboBox<String> cmbLoaiSan, cmbTrangThai;
    private JLabel maDS, maKH, maSan, maDH, loaiSan, ngayBatDau, ngayKetThuc, gioBatDau, gioKetThuc, cacThuTrongTuan, trangThai;
    private JButton btnXacNhan, btnHuy;
    
    public EditFrm(String maDSValue, String maKHValue, String maSanValue, String maDHValue, String loaiSanValue, Date ngayBatDauValue, Date ngayKetThucValue, Date gioBatDauValue, Date gioKetThucValue, List<String> cacThuTrongTuanValue, String trangThaiValue) {
        initComponents();
        
        txtMaDS.setText(maDSValue);
        txtMaKH.setText(maKHValue);
        txtMaSan.setText(maSanValue);
        txtMaDH.setText(maDHValue);
        cmbLoaiSan.setSelectedItem(loaiSanValue);
        spNgayBatDau.setValue(ngayBatDauValue);
        spNgayKetThuc.setValue(ngayKetThucValue);
        spGioBatDau.setValue(gioBatDauValue);
        spGioKetThuc.setValue(gioKetThucValue);
        cmbTrangThai.setSelectedItem(trangThaiValue);
        
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
    
    public void initComponents() {
        frame = new JFrame("Sửa thông tin đặt sân");
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
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
        trangThai = new JLabel("Trạng thái");

        txtMaDS= new JTextField(10);
        // txtMaDS.setEditable(false); 
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
        JSpinner.DateEditor GioBatDau = new JSpinner.DateEditor(spGioBatDau, "HH:mm");
        JSpinner.DateEditor GioKetThuc = new JSpinner.DateEditor(spGioKetThuc, "HH:mm");
        spGioBatDau.setEditor(GioBatDau);
        spGioKetThuc.setEditor(GioKetThuc);
        
        btnXacNhan = new JButton("Xác nhận");
        btnHuy = new JButton("Hủy");
        
        panel.setLayout(new GridLayout(0, 2));
        panel.add(maDS);
        panel.add(txtMaDS);
        panel.add(maKH);
        panel.add(txtMaKH);
        panel.add(maSan);
        panel.add(txtMaSan);
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
        panel.add(trangThai);
        panel.add(cmbTrangThai);
        
        panel.add(btnHuy);
        panel.add(btnXacNhan);
        
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDatabase();
            }
        });
        
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
    
    private void updateDatabase() {
        // Kết nối tới cơ sở dữ liệu
        String URL = "jdbc:mysql://localhost:3306/qlsancau";
        String USER = "root";
        String PASS = "";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // Chuẩn bị câu lệnh SQL để thêm dữ liệu vào bảng
            String sql = "UPDATE danhsachdatsan SET MaKH=?, MaSan=?, MaDH=?, LoaiSan=?, NgayBatDau=?, NgayKetThuc=?, GioBatDau=?, GioKetThuc=?, Thu_2=?, Thu_3=?, Thu_4=?, Thu_5=?, Thu_6=?, Thu_7=?, ChuNhat=?, TrangThai=?, SoGioThue=? WHERE MaDS=?";

            PreparedStatement st = conn.prepareStatement(sql);
        
            // Lấy giá trị của giờ bắt đầu và giờ kết thúc từ JSpinner
            Timestamp gioBatDau = new Timestamp(((java.util.Date) spGioBatDau.getValue()).getTime());
            Timestamp gioKetThuc = new Timestamp(((java.util.Date) spGioKetThuc.getValue()).getTime());
    
            // Tính số giờ
            long milliseconds = gioKetThuc.getTime() - gioBatDau.getTime();
            int hours = (int) (milliseconds / (1000 * 60 * 60));
            st.setInt(18, hours);

            st.setString(1, txtMaKH.getText());
            st.setString(2, txtMaSan.getText());
            st.setString(3, txtMaDH.getText());
            st.setString(4, cmbLoaiSan.getSelectedItem().toString());
            st.setTimestamp(5, new Timestamp(((java.util.Date) spNgayBatDau.getValue()).getTime()));
            st.setTimestamp(6, new Timestamp(((java.util.Date) spNgayKetThuc.getValue()).getTime()));
            st.setTimestamp(7, gioBatDau);
            st.setTimestamp(8, gioKetThuc);
        
            // Xử lý các ô checkbox và thiết lập giá trị cho tham số trong câu lệnh SQL
            st.setString(9, cbThu2.isSelected() ? "1" : "0");
            st.setString(10, cbThu3.isSelected() ? "1" : "0");
            st.setString(11, cbThu4.isSelected() ? "1" : "0");
            st.setString(12, cbThu5.isSelected() ? "1" : "0");
            st.setString(13, cbThu6.isSelected() ? "1" : "0");
            st.setString(14, cbThu7.isSelected() ? "1" : "0");
            st.setString(15, cbChuNhat.isSelected() ? "1" : "0");
        
            st.setString(16, cmbTrangThai.getSelectedItem().toString());
        
            st.setString(17, txtMaDS.getText());

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
    }
}
