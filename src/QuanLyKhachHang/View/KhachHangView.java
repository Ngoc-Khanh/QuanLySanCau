/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import DBconnect.KhachHangController;
import MainMenu.MainMenuView;
import Model.KhachHang;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Khanh
 */
public class KhachHangView extends MainMenuView {

    private JButton AddBtn, EditBtn, SaveBtn, DeleteBtn, ClearBtn, SearchBtn, CapnhatBtn, ExportBtn;
    private JScrollPane JScrollPaneTable;
    private JTable Table;
    private JTextField Searchtf, MaKHtf, TenKHtf, SDTtf, SoLanDattf;
    private JLabel MaKHlbl, TenKHlbl, SDTlbl, SoLanDatlbl;
    private DefaultTableModel tblModel;
    private final String[] columnNames = new String[]{
        "Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Số Lần Đặt"};
    private final Object data = new Object[][]{};
    private List<KhachHang> listKH = new ArrayList<>();

    int Makh;

    public KhachHangView() {
        initComponents();
    }

    private void initComponents() {
        JScrollPaneTable = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MaKHlbl = new JLabel("Mã khách hàng:");
        TenKHlbl = new JLabel("Tên khách hàng:");
        SDTlbl = new JLabel("Số điện thoại:");
        SoLanDatlbl = new JLabel("Số Lần Đặt");
        JLabel label = new JLabel("Quản Lý Khách Hàng");
        Font font = new Font(label.getFont().getName(), Font.BOLD, 25); // Đặt font chữ
        label.setFont(font); // Áp dụng font cho JLabel

        MaKHtf = new JTextField(15);
        MaKHtf.setEditable(false);//không cho sửa
        TenKHtf = new JTextField(15);
        SDTtf = new JTextField(15);
        SoLanDattf = new JTextField(15);
        SoLanDattf.setEditable(false);//không cho sửa
        Searchtf = new JTextField();
        Searchtf.setPreferredSize(new Dimension(200, 25));

        AddBtn = new JButton("Thêm");
        EditBtn = new JButton("Sửa");
        SaveBtn = new JButton("Lưu");
        DeleteBtn = new JButton("Xoá");
        ClearBtn = new JButton("Nhập lại");
        SearchBtn = new JButton("Tìm kiếm");
        CapnhatBtn = new JButton("Cập nhật số lần đặt");
        ExportBtn = new JButton("Xuất Excel");

        JScrollPaneTable = new JScrollPane();
        Table = new JTable();
        tblModel = new DefaultTableModel((Object[][]) data, columnNames);
        Table.setModel(tblModel);
        JScrollPaneTable.setViewportView(Table);
        Table.getTableHeader().setReorderingAllowed(false);//không cho sửa các tiêu đề cột
        JScrollPaneTable.setPreferredSize(new Dimension(1247, 300));
        Table.setDefaultEditor(Object.class, null);//không cho sửa dữ liệu trong bảng

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(JScrollPaneTable);

        panel.add(label);
        panel.add(MaKHlbl);
        panel.add(TenKHlbl);
        panel.add(SDTlbl);
        panel.add(SoLanDatlbl);

        panel.add(MaKHtf);
        panel.add(TenKHtf);
        panel.add(SDTtf);
        panel.add(SoLanDattf);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS)); // Sử dụng BoxLayout để các nút được sắp xếp theo chiều ngang

        // Thêm các nút vào panel
        buttonPanel.add(AddBtn);
        buttonPanel.add(Box.createHorizontalStrut(30)); // Tạo một khoảng cách ngang 30 pixel giữa các nút
        buttonPanel.add(EditBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(SaveBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(DeleteBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(ClearBtn);
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(ExportBtn);

        // Thêm panel chứa các nút vào phần dưới cùng của panel chính
        panel.add(buttonPanel);

        panel.add(Searchtf);
        panel.add(SearchBtn);
        panel.add(CapnhatBtn);

        //Bang item
        layout.putConstraint(SpringLayout.WEST, label, 280, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, label, 15, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, Searchtf, 140, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Searchtf, 65, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SearchBtn, 210, SpringLayout.WEST, Searchtf);
        layout.putConstraint(SpringLayout.NORTH, SearchBtn, 65, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.EAST, CapnhatBtn, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, CapnhatBtn, 65, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, JScrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, JScrollPaneTable, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, JScrollPaneTable, -19, SpringLayout.EAST, panel);
        //cot 1
        layout.putConstraint(SpringLayout.WEST, MaKHlbl, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaKHlbl, 20, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, MaKHtf, 190, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, MaKHtf, 20, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, TenKHlbl, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TenKHlbl, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, TenKHtf, 190, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, TenKHtf, 60, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, SDTlbl, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, SDTlbl, 20, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, SDTtf, 490, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, SDTtf, 20, SpringLayout.SOUTH, JScrollPaneTable);
        //cot2

        layout.putConstraint(SpringLayout.WEST, SoLanDatlbl, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, SoLanDatlbl, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, SoLanDattf, 490, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, SoLanDattf, 60, SpringLayout.SOUTH, JScrollPaneTable);

        //nut

        layout.putConstraint(SpringLayout.WEST, buttonPanel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, buttonPanel, -20, SpringLayout.SOUTH, panel); // Đặt panel ở phía dưới cùng của panel chính

        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        KhachHangController controller = new KhachHangController();
        listKH = controller.getKhachHangList();
        this.KHList(listKH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel menu = super.menu();
        mainPanel.add(menu, BorderLayout.WEST);
        mainPanel.add(panel, BorderLayout.CENTER);

        add(mainPanel);
        setTitle("Quản lý khách hàng");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        controller.setButtonListener(AddBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String TenKH = TenKHtf.getText();
                String SDT = SDTtf.getText();
//                String SoLanDatText = SoLanDattf.getText();

                // Kiểm tra nếu SDT hoặc SoLanDat không phải là số
                if (!isNumeric(SDT)) {
                    JOptionPane.showMessageDialog(null, "Bạn phải nhập số cho SĐ  T", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng việc thêm khách hàng và hiển thị thông báo lỗi
                }
                if (TenKH.isEmpty() || SDT.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bạn phải nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng việc thêm khách hàng và hiển thị thông báo lỗi
                }

//                int SoLanDat = Integer.parseInt(SoLanDatText);
                KhachHang kh = new KhachHang(TenKH, SDT);
//            System.out.println("abcscs: " + TenKHtf.getText());
                controller.addKH(kh);
                JOptionPane.showMessageDialog(null, "Khách hàng đã được thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                listKH = controller.getKhachHangList();
                KHList(listKH);
                clearFields();

            }
        });

        controller.setButtonListener(ClearBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }

        });

        controller.setButtonListener(DeleteBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Table.getSelectedRow();
                if (selectedRow == -1) {
                    // Nếu không có hàng nào được chọn, hiển thị thông báo lỗi
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa", "Lỗi Xóa", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Lấy dữ liệu của hàng được chọn và hiển thị lên các ô text
                int MaKH = (int) Table.getValueAt(selectedRow, 0);
                String TenKH = (String) Table.getValueAt(selectedRow, 1);
                String SDT = (String) Table.getValueAt(selectedRow, 2);
                int SoLanDat = (int) Table.getValueAt(selectedRow, 3);

                // Hiển thị dữ liệu lên các ô text
                MaKHtf.setText(String.valueOf(MaKH)); // Hiển thị ID
                TenKHtf.setText(TenKH);
                SDTtf.setText(SDT);
                SoLanDattf.setText(String.valueOf(SoLanDat));

                int Makh = (int) Table.getValueAt(selectedRow, 0);
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    controller.deletesan(Makh);
                    listKH = controller.getKhachHangList();
                    KHList(listKH);
                    clearFields();
                }
            }
        });

        controller.setButtonListener(EditBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra xem có hàng nào được chọn không
                if (Table.getSelectedRow() != -1) {
                    // Lấy hàng đã chọn
                    int selectedRow = Table.getSelectedRow();

                    // Lấy dữ liệu từ bảng và đặt vào các JLabel
                    int MaKH = (int) Table.getValueAt(selectedRow, 0);
                    String TenKH = (String) Table.getValueAt(selectedRow, 1);
                    String SDT = (String) Table.getValueAt(selectedRow, 2);
                    int SoLanDat = (int) Table.getValueAt(selectedRow, 3);

                    MaKHtf.setText(String.valueOf(MaKH)); // Chuyển đổi số nguyên thành chuỗi
                    TenKHtf.setText(TenKH);
                    SDTtf.setText(SDT); //
                    SoLanDattf.setText(String.valueOf(SoLanDat));

//                    Makh = (int) Table.getValueAt(selectedRow, 0);
                }
            }
        });

        controller.setButtonListener(SearchBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = Searchtf.getText().trim(); // Lấy từ khóa tìm kiếm từ JTextField và loại bỏ dấu cách thừa

                // Tạo một danh sách tạm thời để lưu kết quả tìm kiếm
                List<KhachHang> searchResult = new ArrayList<>();

                // Duyệt qua danh sách sách hiện tại và kiểm tra xem từ khóa tìm kiếm có trong sách hay không
                for (KhachHang kh : listKH) {
                    if (kh.getTenKH().toLowerCase().contains(keyword.toLowerCase()) || kh.getSDT().contains(keyword)) {
                        // Nếu tìm thấy từ khóa trong tên sách, thêm sách vào danh sách kết quả
                        searchResult.add(kh);
                    }
                }
                // Hiển thị kết quả tìm kiếm trên bảng
                KHList(searchResult);
            }

        });
        controller.setButtonListener(SaveBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra xem người dùng đã nhập đầy đủ thông tin hay chưa
                String TenKH = TenKHtf.getText();
                String SDT = SDTtf.getText();
                String SoLanDatstr = SoLanDattf.getText();

                if (TenKH.isEmpty() || SDT.isEmpty() || SoLanDatstr.isEmpty()) {
                    // Nếu có một trường nào đó chưa được điền, hiển thị thông báo và không thực hiện lưu
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }

                try {
                    // Chuyển đổi dữ liệu về số nguyên
                    int SoLanDat = Integer.parseInt(SoLanDatstr);

                    // Kiểm tra xem có dòng nào được chọn không
                    int selectedRow = Table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để chỉnh sửa.");
                        return;
                    }

                    // Lấy ID của item được chọn
                    int Makh = (int) Table.getValueAt(selectedRow, 0);

                    // Gọi phương thức trong controller để cập nhật thông tin của mục đã chọn
                    KhachHang kh = new KhachHang(Makh, TenKH, SDT, SoLanDat);
                    controller.updatesan(kh);

                    // Cập nhật lại dòng trong bảng sau khi lưu thành công
                    listKH = controller.getKhachHangList();
                    KHList(listKH);
                    clearFields();
                } catch (NumberFormatException ex) {
                    // Xử lý nếu có lỗi khi chuyển đổi dữ liệu về số nguyên
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số cho ô giá ");
                }
            }
        });

        controller.setButtonListener(ExportBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Bảng giá sân");
                XSSFRow row = null;
                Cell cell = null;

                row = sheet.createRow(0);

//                System.out.println("Size of listSan: " + listSan.size());
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("MaSan");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("TenSan");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("LoaiSan");

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("GiaSan");

                List<KhachHang> list1 = new KhachHangController().getKhachHangList();
//
                if (list1 != null) {
                    FileOutputStream fos = null;
                    try {
                        int s = list1.size();
                        for (int i = 0; i < s; i++) {
                            KhachHang s1 = list1.get(i);
                            row = sheet.createRow(1 + i);

                            cell = row.createCell(0, CellType.NUMERIC);
                            cell.setCellValue(s1.getMaKH());

                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(s1.getTenKH());

                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(s1.getSDT());

                            cell = row.createCell(3, CellType.NUMERIC);
                            cell.setCellValue(s1.getSoLanDat());

                        }
                        File f = new File("F:\\thongtinkhachhang.xlsx");
                        fos = new FileOutputStream(f);
                        workbook.write(fos);
                        fos.close();
                        JOptionPane.showMessageDialog(rootPane, "Excel Success!.File path: " + f.getPath());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

controller.setButtonListener(CapnhatBtn, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  controller.CapNhatSoLanDat();
                 // Cập nhật lại dòng trong bảng sau khi lưu thành công
                  listKH = controller.getKhachHangList();
                  KHList(listKH);
            }

        });
    }

    private void KHList(List<KhachHang> updatedKHList) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) JScrollPaneTable.getViewport().getView()).getModel();
        model.setRowCount(0); // Xóa dữ liệu hiện có trong bảng

        // Thêm dữ liệu mới từ danh sách updatedKHList vào bảng
        for (KhachHang kh : updatedKHList) {
            model.addRow(new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getSDT(),
                kh.getSoLanDat(),});
        }
    }

    public void clearFields() {
        MaKHtf.setText("");
        TenKHtf.setText("");
        SDTtf.setText("");
        SoLanDattf.setText("");
    }

    private boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
//dòng mã char c = str.charAt(i);trong đoạn mã trước đó đang lấy ký tự ở vị 
//trí i trong chuỗi str và gán vào biến c.
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
