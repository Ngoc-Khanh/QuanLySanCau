package MainMenu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import QuanLyDatSan.DatSanController;
import QuanLyDatSan.DatSanModel;
import QuanLyDatSan.DatSanView;

public class MainMenuView extends JFrame {
    private String[] menuItems;
    private JList<String> menuList;
    private JScrollPane scrollPane;
    private JPanel datSanPanel;

    public MainMenuView() {
        setTitle("Quản lý đặt sân cầu");
        setSize(1200,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1,2));

        menuItems = new String[] {"Quản lý đặt sân", "Hóa đơn", "..."};
        menuList = new JList<>(menuItems);
        scrollPane = new JScrollPane(menuList);

        getContentPane().add(scrollPane, BorderLayout.WEST);
        setVisible(true);

        // Khởi tạo JPanel cho DatSan
        datSanPanel = new JPanel(new BorderLayout());

        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = menuList.getSelectedValue();
                    if (selectedValue != null && selectedValue.equals("Quản lý đặt sân")) { 
                        showDatSanPanel();
                    }
                }
            }
        });

        getContentPane().add(datSanPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }

    // Phương thức hiển thị DatSanPanel
    private void showDatSanPanel() {
        datSanPanel.removeAll(); // Xóa tất cả các thành phần hiện có trên datSanPanel
        DatSanModel model = new DatSanModel();
        DatSanView view = new DatSanView();
        DatSanController controller = new DatSanController(model, view);
        datSanPanel.add(view); // Thêm DatSanView vào datSanPanel
        revalidate(); // Cập nhật lại giao diện
        repaint(); // Vẽ lại giao diện
    }

    public static void main(String[] args) {
        new MainMenuView();
    }
}
