package QuanLyDatSan;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {
    private JTextField txtMaDS, txtMaKH, txtMaSan, txtMaDH, txtLoaiSan,
            txtNgayBatDau, txtNgayKetThuc, txtGioBatDau, txtGioKetThuc,
            txtSoGioThue, txtTrangThai;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel, btnReload;
    private JCheckBox cbThu2, cbThu3, cbThu4, cbThu5, cbThu6, cbThu7, cbChuNhat;
    public JTable dataTable;

    public View() {
        setTitle("Quản lý đặt sân");
        setSize(1200, 700);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel chứa các thành phần nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label và TextField cho Mã DS
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Mã DS:"), gbc);
        txtMaDS = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(txtMaDS, gbc);

        // Label và TextField cho Mã KH
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Mã KH:"), gbc);
        txtMaKH = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(txtMaKH, gbc);
        
        // Label và TextField cho Mã Sân
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Mã Sân:"), gbc);
        txtMaSan = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(txtMaSan, gbc);

        // Label và TextField cho Mã ĐH
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Mã ĐH:"), gbc);
        txtMaDH = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(txtMaDH, gbc);

        // Label và TextField cho Loại Sân
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Loại Sân:"), gbc);
        txtLoaiSan = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(txtLoaiSan, gbc);

        // Label và TextField cho Ngày Bắt Đầu
        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Ngày Bắt Đầu:"), gbc);
        txtNgayBatDau = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 5;
        inputPanel.add(txtNgayBatDau, gbc);

        // Label và TextField cho Ngày Kết Thúc
        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Ngày Kết Thúc:"), gbc);
        txtNgayKetThuc = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 6;
        inputPanel.add(txtNgayKetThuc, gbc);

        // Label và TextField cho Giờ Bắt Đầu
        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(new JLabel("Giờ Bắt Đầu:"), gbc);
        txtGioBatDau = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 7;
        inputPanel.add(txtGioBatDau, gbc);

        // Label và TextField cho Giờ Kết Thúc
        gbc.gridx = 0;
        gbc.gridy = 8;
        inputPanel.add(new JLabel("Giờ Kết Thúc:"), gbc);
        txtGioKetThuc = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 8;
        inputPanel.add(txtGioKetThuc, gbc);

        // Label và TextField cho Số Giờ Thuê
        gbc.gridx = 0;
        gbc.gridy = 9;
        inputPanel.add(new JLabel("Số Giờ Thuê:"), gbc);
        txtSoGioThue = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 9;
        inputPanel.add(txtSoGioThue, gbc);
        
        // Label và TextField cho Trạng thái
        gbc.gridx = 0;
        gbc.gridy = 10;
        inputPanel.add(new JLabel("Trạng thái"), gbc);
        txtTrangThai = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 10;
        inputPanel.add(txtTrangThai, gbc);
        
        // Thêm checkbox
        gbc.gridx = 0;
        gbc.gridy = 11;
        cbThu2 = new JCheckBox("Thứ 2");
        inputPanel.add(cbThu2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 11;
        cbThu3 = new JCheckBox("Thứ 3");
        inputPanel.add(cbThu3, gbc);
        gbc.gridx = 0;
        gbc.gridy = 12;
        cbThu4 = new JCheckBox("Thứ 4");
        inputPanel.add(cbThu4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 12;
        cbThu5 = new JCheckBox("Thứ 5");
        inputPanel.add(cbThu5, gbc);
        gbc.gridx = 0;
        gbc.gridy = 13;
        cbThu6 = new JCheckBox("Thứ 6");
        inputPanel.add(cbThu6, gbc);
        gbc.gridx = 1;
        gbc.gridy = 13;
        cbThu7 = new JCheckBox("Thứ 7");
        inputPanel.add(cbThu7, gbc);
        gbc.gridx = 0;
        gbc.gridy = 14;
        cbChuNhat = new JCheckBox("Chủ Nhật");
        inputPanel.add(cbChuNhat, gbc);

        // Các button
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnAdd = new JButton("Thêm");
        inputPanel.add(btnAdd, gbc);
        gbc.gridx = 1;
        gbc.gridy = 15;
        btnEdit = new JButton("Sửa");
        inputPanel.add(btnEdit, gbc);
        gbc.gridx = 0;
        gbc.gridy = 16;
        btnDelete = new JButton("Xóa");
        inputPanel.add(btnDelete, gbc);
        gbc.gridx = 1;
        gbc.gridy = 16;
        btnCancel = new JButton("Thoát");
        inputPanel.add(btnCancel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 17;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        btnReload = new JButton("Reload");
        inputPanel.add(btnReload, gbc);

        // Panel chứa bảng hiển thị dữ liệu
        JPanel dataPanel = new JPanel(new BorderLayout());

        // Tạo bảng để hiển thị dữ liệu
        String[] columnNames = {"Mã DS", "Mã KH", "Mã Sân", "Mã ĐH", "Loại Sân",
            "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giờ Bắt Đầu", "Giờ Kết Thúc",
            "Số Giờ Thuê", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7",
            "Chủ nhật", "Trạng thái"};
        dataTable = new JTable(new DefaultTableModel(columnNames, 0));
        JScrollPane scrollPane = new JScrollPane(dataTable);
        dataPanel.add(scrollPane, BorderLayout.CENTER);

        // Gộp các panel vào frame chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.EAST);
        mainPanel.add(dataPanel, BorderLayout.CENTER);

        // Khóa các JTextField
        txtMaDS.setEditable(false);
        txtMaKH.setEditable(false);
        txtMaSan.setEditable(false);
        txtMaDH.setEditable(false);
        txtLoaiSan.setEditable(false);
        txtNgayBatDau.setEditable(false);
        txtNgayKetThuc.setEditable(false);
        txtGioBatDau.setEditable(false);
        txtGioKetThuc.setEditable(false);
        txtSoGioThue.setEditable(false);
        txtTrangThai.setEditable(false);
        cbThu2.setEnabled(false);
        cbThu3.setEnabled(false);
        cbThu4.setEnabled(false);
        cbThu5.setEnabled(false);
        cbThu6.setEnabled(false);
        cbThu7.setEnabled(false);
        cbChuNhat.setEnabled(false);
       
        add(mainPanel);
        setVisible(true);
        
        dataTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = dataTable.getSelectedRow();
                    if (selectedRow != -1) {
                        txtMaDS.setText((String) dataTable.getValueAt(selectedRow, 0));
                        txtMaKH.setText((String) dataTable.getValueAt(selectedRow, 1));
                        txtMaSan.setText((String) dataTable.getValueAt(selectedRow, 2));
                        txtMaDH.setText((String) dataTable.getValueAt(selectedRow, 3));
                        txtLoaiSan.setText((String) dataTable.getValueAt(selectedRow, 4));
                        txtNgayBatDau.setText((String) dataTable.getValueAt(selectedRow, 5));
                        txtNgayKetThuc.setText((String) dataTable.getValueAt(selectedRow, 6));
                        txtGioBatDau.setText((String) dataTable.getValueAt(selectedRow, 7));
                        txtGioKetThuc.setText((String) dataTable.getValueAt(selectedRow, 8));
//                        txtSoGioThue.setText((String) dataTable.getValueAt(selectedRow, 9));
                        txtSoGioThue.setText(calculateSoGioThue(selectedRow));
                        txtTrangThai.setText(convertTrangThai((String) dataTable.getValueAt(selectedRow, 17)));
                        
                        cbThu2.setSelected(dataTable.getValueAt(selectedRow, 10).equals("1"));
                        cbThu3.setSelected(dataTable.getValueAt(selectedRow, 11).equals("1"));
                        cbThu4.setSelected(dataTable.getValueAt(selectedRow, 12).equals("1"));
                        cbThu5.setSelected(dataTable.getValueAt(selectedRow, 13).equals("1"));
                        cbThu6.setSelected(dataTable.getValueAt(selectedRow, 14).equals("1"));
                        cbThu7.setSelected(dataTable.getValueAt(selectedRow, 15).equals("1"));
                        cbChuNhat.setSelected(dataTable.getValueAt(selectedRow, 16).equals("1"));
                    }
                }
            }
        });
    }
    
    private String calculateSoGioThue(int selectedRow) {
        String gioBatDauStr = (String) dataTable.getValueAt(selectedRow, 7);
        String gioKetThucStr = (String) dataTable.getValueAt(selectedRow, 8);

        // Phân tách chuỗi thời gian thành giờ, phút và giây
        String[] gioBatDauParts = gioBatDauStr.split(":");
        String[] gioKetThucParts = gioKetThucStr.split(":");

        // Chuyển đổi các phần giờ và phút thành số nguyên
        int gioBatDau = Integer.parseInt(gioBatDauParts[0]);
        int phutBatDau = Integer.parseInt(gioBatDauParts[1]);
        int gioKetThuc = Integer.parseInt(gioKetThucParts[0]);
        int phutKetThuc = Integer.parseInt(gioKetThucParts[1]);

        // Tính toán số giờ thuê
        int soGioThue = gioKetThuc - gioBatDau;
        if (phutKetThuc < phutBatDau) {
            soGioThue--; // Giảm số giờ thuê nếu phút kết thúc nhỏ hơn phút bắt đầu
        }

        // Hiển thị số giờ thuê kèm đơn vị
        return soGioThue + " giờ";
    }

    // Phương thức này để lấy thông tin từ các trường nhập liệu
    public String[] getDatsanInfo() {
        String[] info = new String[18];
        info[0] = txtMaDS.getText();
        info[1] = txtMaKH.getText();
        info[2] = txtMaSan.getText();
        info[3] = txtMaDH.getText();
        info[4] = txtLoaiSan.getText();
        info[5] = txtNgayBatDau.getText();
        info[6] = txtNgayKetThuc.getText();
        info[7] = txtGioBatDau.getText();
        info[8] = txtGioKetThuc.getText();
        info[9] = txtSoGioThue.getText();
        info[10] = String.valueOf(cbThu2.isSelected());
        info[11] = String.valueOf(cbThu3.isSelected());
        info[12] = String.valueOf(cbThu4.isSelected());
        info[13] = String.valueOf(cbThu5.isSelected());
        info[14] = String.valueOf(cbThu6.isSelected());
        info[15] = String.valueOf(cbThu7.isSelected());
        info[16] = String.valueOf(cbChuNhat.isSelected());
        info[17] = txtTrangThai.getText();
        return info;
    }

    // Phương thức này để thêm một trình nghe cho nút Add
    public void addAddListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Edit
    public void addEditListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Delete
    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Cancel
    public void addCancelListener(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }

    // Phương thức này để thêm một trình nghe cho nút Reload
    public void addReloadListener(ActionListener listener) {
        btnReload.addActionListener(listener);
    }
    
    // Phương thức này để cập nhật dữ liệu trong bảng từ cơ sở dữ liệu
    public void updateTableData(String[][] data) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        for (String[] row : data) {
            model.addRow(row); // Thêm dữ liệu mới vào bảng
        }
    }
    
    private String convertTrangThai(String value) {
        if (value != null) {
            return value.equals("1") ? "Còn" : "Hết";
        }
        return ""; // hoặc bạn có thể trả về một giá trị mặc định khác nếu cần
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new View());
    }
}
