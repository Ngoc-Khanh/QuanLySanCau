/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLySanCau;

import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Krug
 */
public class SanCauFrm extends JFrame {
    private JTextField txtMaDS, txtMaKH, txtMaDH, txtLoaiSan, txtTienSan, txtTimKiem;
    private JSpinner spNgayBatDau, spNgayKetThuc, spGioBatDau, spGioKetThuc;
    private SpinnerDateModel dateModelBatDau, dateModelKetThuc ,timeModelBatDau, timeModelKetThuc;
    private JCheckBox cbThu2, cbThu3, cbThu4, cbThu5, cbThu6, cbThu7, cbChuNhat;
    private JComboBox cmbTrangThai;
    private JTable tbDatSan;
    private JButton btnThem, btnSua, btnXoa, btnThoat, btnTimKiem, btnThemDichVu;
    private JLabel loaiSan, ngayBatDau, ngayKetThuc, gioBatDau, gioKetThuc,
            giaSan, cacThuTrongTuan;
    private JScrollPane JScrollPaneTable;
    private DefaultTableModel tblModel;
    private static boolean isClosed = true;
    private final String[] tenCot = new String[] {
        "Mã đặt sân", "Mã khách hàng", "Mã sân", "Mã đặt hàng", "Loại sân", "Ngày bắt đầu", "Ngày kết thúc",
        "Giờ bắt đầu", "Giờ kết thúc", "Số giờ thuê", "Thứ 2", "Thứ 3", "Thứ 4",
        "Thứ 5", "Thứ 6", "Thứ 7", "Chủ nhật", "Giá sân", "Trạng thái"
    };
    private final Object data = new Object[][]{};
    private List<SanCau> listSanCau = new ArrayList<>();
            
    public SanCauFrm() {
        initForm();
    }
    
    private void initForm() {
        setTitle("Quản lý đặt sân cầu");
        setSize(800, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        JScrollPaneTable = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loaiSan = new JLabel("Loại sân");
        ngayBatDau = new JLabel("Đặt từ ngày");
        ngayKetThuc = new JLabel("Đến ngày");
        gioBatDau = new JLabel("Khung giờ");
        gioKetThuc = new JLabel("Đến giờ");
        giaSan = new JLabel("Giá sân");
        cacThuTrongTuan = new JLabel("Các thứ trong tuần");
        
//        txtMaDS = new JTextField(15);
//        txtMaKH = new JTextField(15);
//        txtMaDH = new JTextField(15);
        txtLoaiSan = new JTextField(15);
        txtTienSan = new JTextField(15);
        txtTimKiem = new JTextField(15);
        // Các button, combobox, checkbox
        cmbTrangThai = new JComboBox<>(new String[] {"Hết sân", "Còn sân"});
        cbThu2 = new JCheckBox("Thứ 2");
        cbThu3 = new JCheckBox("Thứ 3");
        cbThu4 = new JCheckBox("Thứ 4");
        cbThu5 = new JCheckBox("Thứ 5");
        cbThu6 = new JCheckBox("Thứ 6");
        cbThu7 = new JCheckBox("Thứ 7");
        cbChuNhat = new JCheckBox("Chủ nhật");
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnThoat = new JButton("Thoát");
        btnTimKiem = new JButton("Tìm kiếm");
        btnThemDichVu = new JButton("Thêm dịch vụ");

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

  
        JScrollPaneTable = new JScrollPane();
        tbDatSan = new JTable();
        tblModel = new DefaultTableModel((Object[][]) data, tenCot);
        tbDatSan.setModel(tblModel);
        JScrollPaneTable.setViewportView(tbDatSan);
        JScrollPaneTable.setPreferredSize(new Dimension(1247,300));
        
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(JScrollPaneTable);
        
        panel.add(loaiSan);
        panel.add(ngayBatDau);
        panel.add(ngayKetThuc);
        panel.add(gioBatDau);
        panel.add(gioKetThuc);
//        panel.add(giaSan);
        panel.add(cacThuTrongTuan);
        
//        panel.add(txtMaDS);
//        panel.add(txtMaKH);
//        panel.add(txtMaDH);
        panel.add(txtLoaiSan);
//        panel.add(txtTienSan);
        panel.add(txtTimKiem);
        
        panel.add(spNgayBatDau);
        panel.add(spNgayKetThuc);
        panel.add(spGioBatDau);
        panel.add(spGioKetThuc);
        
        panel.add(cmbTrangThai);
        panel.add(cbThu2);
        panel.add(cbThu3);
        panel.add(cbThu4);
        panel.add(cbThu5);
        panel.add(cbThu6);
        panel.add(cbThu7);
        panel.add(cbChuNhat);
        
        panel.add(btnThem);
        panel.add(btnSua);
        panel.add(btnXoa);
        panel.add(btnThoat);
        panel.add(btnTimKiem);
//        panel.add(btnThemDichVu);
        
        layout.putConstraint(SpringLayout.WEST, txtTimKiem ,300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtTimKiem ,20, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, btnTimKiem ,500, SpringLayout.WEST, txtTimKiem);
        layout.putConstraint(SpringLayout.NORTH, btnTimKiem ,17, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, JScrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, JScrollPaneTable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, JScrollPaneTable, -19, SpringLayout.EAST, panel);
        // Cột 1
        layout.putConstraint(SpringLayout.WEST, loaiSan, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, loaiSan, 20, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, txtLoaiSan, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtLoaiSan, 20, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, ngayBatDau, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngayBatDau, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, spNgayBatDau, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, spNgayBatDau, 60, SpringLayout.SOUTH, JScrollPaneTable);
        
        layout.putConstraint(SpringLayout.WEST, ngayKetThuc, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngayKetThuc, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, spNgayKetThuc, 260, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, spNgayKetThuc, 60, SpringLayout.SOUTH, JScrollPaneTable);
        
        layout.putConstraint(SpringLayout.WEST, gioBatDau, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioBatDau, 100, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, spGioBatDau, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, spGioBatDau, 100, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, gioKetThuc, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioKetThuc, 100, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, spGioKetThuc, 260, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, spGioKetThuc, 100, SpringLayout.SOUTH, JScrollPaneTable);
        
        layout.putConstraint(SpringLayout.WEST, cacThuTrongTuan, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cacThuTrongTuan, 140, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, cbThu2, 140, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cbThu2, 136, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, cbThu3, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cbThu3, 136, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, cbThu4, 260, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cbThu4, 136, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, cbThu5, 320, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cbThu5, 136, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, cbThu6, 380, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cbThu6, 136, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, cbThu7, 440, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cbThu7, 136, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, cbChuNhat, 500, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cbChuNhat, 136, SpringLayout.SOUTH, JScrollPaneTable);
                                                 
        // Cột 2
        layout.putConstraint(SpringLayout.WEST, cmbTrangThai, 260, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cmbTrangThai, 19, SpringLayout.SOUTH, JScrollPaneTable);
              
        // Buttons
        layout.putConstraint(SpringLayout.EAST, btnThem, -32, SpringLayout.WEST, btnSua);
        layout.putConstraint(SpringLayout.NORTH, btnThem, 25, SpringLayout.SOUTH, JScrollPaneTable);
        
        layout.putConstraint(SpringLayout.EAST, btnSua, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, btnSua, 25, SpringLayout.SOUTH, JScrollPaneTable);
         
        layout.putConstraint(SpringLayout.EAST, btnXoa, -32, SpringLayout.WEST, btnThoat);
        layout.putConstraint(SpringLayout.NORTH, btnXoa, 85, SpringLayout.SOUTH, JScrollPaneTable);
        
        layout.putConstraint(SpringLayout.EAST, btnThoat, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, btnThoat, 85, SpringLayout.SOUTH, JScrollPaneTable);
        
        add(panel);       
        
        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        SanCauDAO controller = new SanCauDAO();
        listSanCau = controller.getSanCauList();
        this.sanCauList(listSanCau);
        
        
        // Thêm bộ lắng nghe sự kiện cho JTable
        tbDatSan.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Kiểm tra xem người dùng đã chọn một hàng chưa
                if (!e.getValueIsAdjusting()) {
                    // Lấy chỉ số của hàng được chọn
                    int selectedRow = tbDatSan.getSelectedRow();
                    if (selectedRow != -1) {
                        // Lấy dữ liệu từ hàng được chọn và hiển thị lên các spinner
                        SanCau selectedSanCau = listSanCau.get(selectedRow);
                        txtLoaiSan.setText(selectedSanCau.getLoaiSan());
                        spNgayBatDau.setValue(selectedSanCau.getNgayBatDau());
                        spNgayKetThuc.setValue(selectedSanCau.getNgayKetThuc());
                        spGioBatDau.setValue(selectedSanCau.getGioBatDau());
                        spGioKetThuc.setValue(selectedSanCau.getGioKetThuc());
                        
                        boolean trangThai = selectedSanCau.isTrangThai();
                        String trangThaiString = trangThai ? "Hết sân" : "Còn sân";
                        cmbTrangThai.setSelectedItem(trangThaiString);
                        
                        cbThu2.setSelected(selectedSanCau.isThu_2());
                        cbThu3.setSelected(selectedSanCau.isThu_3());
                        cbThu4.setSelected(selectedSanCau.isThu_4());
                        cbThu5.setSelected(selectedSanCau.isThu_5());
                        cbThu6.setSelected(selectedSanCau.isThu_6());
                        cbThu7.setSelected(selectedSanCau.isThu_7());
                        cbChuNhat.setSelected(selectedSanCau.isChuNhat());
                    }
                }
            }
        });
        
        btnThem.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent e) {
                ThemFrm.Them();
                refreshTable();
            }
        });
        
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbDatSan.getSelectedRow();
                if (selectedRow != -1) {
                    String maDSValue = tbDatSan.getValueAt(selectedRow, 0).toString(); // Giả sử cột 0 chứa mã đặt sân
                    String maKHValue = tbDatSan.getValueAt(selectedRow, 1).toString(); // Giả sử cột 1 chứa mã khách hàng
                    String maSanValue = tbDatSan.getValueAt(selectedRow, 2).toString(); // Giả sử cột 2 chứa mã sân
                    String maDHValue = tbDatSan.getValueAt(selectedRow, 3).toString(); // Giả sử cột 3 chứa mã đặt hàng
                    String loaiSanValue = tbDatSan.getValueAt(selectedRow, 4).toString(); // Giả sử cột 4 chứa loại sân
                    Date ngayBatDauValue = (Date) tbDatSan.getValueAt(selectedRow, 5); // Giả sử cột 5 chứa ngày bắt đầu
                    Date ngayKetThucValue = (Date) tbDatSan.getValueAt(selectedRow, 6); // Giả sử cột 6 chứa ngày kết thúc
                    Date gioBatDauValue = (Date) tbDatSan.getValueAt(selectedRow, 7); // Giả sử cột 7 chứa giờ bắt đầu
                    Date gioKetThucValue = (Date) tbDatSan.getValueAt(selectedRow, 8);
                    // Giả sử cột 9 đến 15 chứa các ngày trong tuần
                    List<String> cacThuTrongTuanValue = new ArrayList<>();
                    for (int i = 9; i <= 15; i++) {
                        Object value = tbDatSan.getValueAt(selectedRow, i);
                        if (value instanceof Boolean && (boolean) value) {
                            cacThuTrongTuanValue.add("Thứ " + (i - 8)); // Ví dụ: "Thứ 2", "Thứ 3",...
                        } else {
                            // Xử lý khi giá trị không phải là true
                        }
                    }
                    // Khởi tạo form SuaFrm với dữ liệu lấy từ bảng dưới
                    SuaFrm suaFrm = new SuaFrm(maDSValue, maKHValue, maSanValue, maDHValue,
                                   loaiSanValue, ngayBatDauValue, ngayKetThucValue,
                                   gioBatDauValue, gioKetThucValue, cacThuTrongTuanValue);

                    // Hiển thị form SuaFrm
                    suaFrm.initComponents();
                } else {
                    // Hiển thị thông báo cho người dùng nếu không có dòng nào được chọn
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa.");
                }
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbDatSan.getSelectedRow();
                if (selectedRow != -1) {
                    int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dữ liệu không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // Lấy mã đặt sân của dòng được chọn
                        int MaDS = Integer.parseInt(tbDatSan.getValueAt(selectedRow, 0).toString());
                        int MaKH = Integer.parseInt(tbDatSan.getValueAt(selectedRow, 1).toString());
                        int MaSan = Integer.parseInt(tbDatSan.getValueAt(selectedRow, 2).toString());
                        int MaDH = Integer.parseInt(tbDatSan.getValueAt(selectedRow, 3).toString());
                        String LoaiSan = (String) tbDatSan.getValueAt(selectedRow, 4);
                        Date NgayBatDau = (Date) tbDatSan.getValueAt(selectedRow, 5);
                        Date NgayKetThuc = (Date) tbDatSan.getValueAt(selectedRow, 6);
                        Date GioBatDau = (Date) tbDatSan.getValueAt(selectedRow, 7);
                        Date GioKetThuc = (Date) tbDatSan.getValueAt(selectedRow, 8);
                        int SoGioThue = Integer.parseInt(tbDatSan.getValueAt(selectedRow, 9).toString());
                        String Thu_2 = (String) tbDatSan.getValueAt(selectedRow, 10);
                        String Thu_3 = (String) tbDatSan.getValueAt(selectedRow, 11);
                        String Thu_4 = (String) tbDatSan.getValueAt(selectedRow, 12);
                        String Thu_5 = (String) tbDatSan.getValueAt(selectedRow, 13);
                        String Thu_6 = (String) tbDatSan.getValueAt(selectedRow, 14);
                        String Thu_7 = (String) tbDatSan.getValueAt(selectedRow, 15);
                        String ChuNhat = (String) tbDatSan.getValueAt(selectedRow, 16);
                        float TongTienSan = Float.parseFloat(tbDatSan.getValueAt(selectedRow, 17).toString());
                        String TrangThai = (String) tbDatSan.getValueAt(selectedRow, 18);

                        // Gọi phương thức xóa dữ liệu từ lớp DAO
                        SanCauDAO controller = new SanCauDAO();
                        boolean success = controller.deleteItem(MaDS);

                        // Kiểm tra xem việc xóa thành công hay không
                        if (success) {
                            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.");
                            refreshTable(); // Cập nhật bảng sau khi xóa dữ liệu
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa.");
                }
            }            
        });
        
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát khỏi chương trình không?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }
    
    private void sanCauList(List<SanCau> listSanCau) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        model.setRowCount(0);
        for (SanCau item : listSanCau) {
            String trangThai = item.isTrangThai() ? "Hết sân" : "Còn sân";
            String cacThu = (item.isThu_2() && item.isThu_3() && item.isThu_4() && item.isThu_5() && item.isThu_6() && item.isThu_7() && item.isChuNhat()) ? "Đã đặt" : "Chưa đặt";
            model.addRow(new Object[] {
                item.getMaDS(),
                item.getMaKH(),
                item.getMaSan(),
                item.getMaDH(),
                item.getLoaiSan(),
                item.getNgayBatDau(),
                item.getNgayKetThuc(),
                item.getGioBatDau(),
                item.getGioKetThuc(),
                item.getSoGioThue(),
                cacThu,
                cacThu,
                cacThu,
                cacThu,
                cacThu,
                cacThu,
                cacThu,
                item.getTongTienSan(),
                trangThai
            });
        }
    }
    
    public void xoaField() {
        // Xóa dữ liệu trong các JTextField
        txtLoaiSan.setText("");
        txtTienSan.setText("");
        txtTimKiem.setText("");

        // Đặt giá trị mặc định cho các JSpinner
        spNgayBatDau.setValue(new Date()); // Giả sử bạn muốn đặt lại ngày hiện tại
        spNgayKetThuc.setValue(new Date());
        spGioBatDau.setValue(new Date()); // Giả sử bạn muốn đặt lại giờ hiện tại
        spGioKetThuc.setValue(new Date());

        // Xóa chọn các JCheckBox
        cbThu2.setSelected(false);
        cbThu3.setSelected(false);
        cbThu4.setSelected(false);
        cbThu5.setSelected(false);
        cbThu6.setSelected(false);
        cbThu7.setSelected(false);
        cbChuNhat.setSelected(false);

        // Đặt lại giá trị mặc định cho JComboBox
        cmbTrangThai.setSelectedIndex(0); // Giả sử bạn muốn đặt lại trạng thái đầu tiên
    }
    
    public void refreshTable() {
        SanCauDAO controller = new SanCauDAO();
        listSanCau = controller.getSanCauList();
        sanCauList(listSanCau);
    }
}
