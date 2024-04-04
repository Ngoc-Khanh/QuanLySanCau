package MainMenu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.ItemModel;
import QuanLyDatSan.DatSanAddFrm;
import QuanLyDatSan.DatSanController;
import QuanLyDatSan.DatSanModel;
import QuanLyDatSan.DatSanView;
import View.ItemView;
import controller.ItemController;

public class MainMenuView extends JFrame {
    private String[] menuItems;
    private JList<String> menuList;
    private JScrollPane scrollPane;
    private JTable dataTable;

    public MainMenuView() {
        setTitle("Quản lý đặt sân cầu");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuItems = new String[]{"Quản lý đặt sân", "Quản lý dịch vụ", "Hóa đơn", "..."};
        menuList = new JList<>(menuItems);
        scrollPane = new JScrollPane(menuList);

        getContentPane().add(scrollPane, BorderLayout.WEST);

        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = menuList.getSelectedValue();
                    if (selectedValue != null && selectedValue.equals("Quản lý đặt sân")) {
                        showDatSan();
                    }
                    if (selectedValue != null && selectedValue.equals("Quản lý dịch vụ")) {
                    }
                }
            }
        }); 

        setVisible(true);
    }

    public void showDatSan() {
        DatSanView view = new DatSanView();
        DatSanModel model = new DatSanModel();
        DatSanController controller = new DatSanController(model, view);
        view.setVisible(true);
    }

    // Phương thức này để cập nhật dữ liệu trong bảng từ cơ sở dữ liệu
    public void updateTableData(String[][] data) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        for (String[] row : data) {
            model.addRow(row); // Thêm dữ liệu mới vào bảng
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainMenuView view = new MainMenuView();
                MainMenuModel model = new MainMenuModel();
                MainMenuController controller = new MainMenuController(model, view);
                view.setVisible(true);
            }
        });
    }
}
