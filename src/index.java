

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DangNhap.DangNhapModel;
import DangNhap.DangNhapController;
import DangNhap.DangNhapView;

import java.awt.*;

public class index {
    public index() {
        JFrame frame = new JFrame("Menu List Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Tạo danh sách các mục menu
        String[] menuItems = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        
        // Tạo JList
        JList<String> menuList = new JList<>(menuItems);
        
        // Đặt JList trong JScrollPane để cho phép cuộn nếu cần
        JScrollPane scrollPane = new JScrollPane(menuList);
        
        // Đặt vị trí của JScrollPane về phía bên trái
        frame.getContentPane().add(scrollPane, BorderLayout.WEST);
        
        // Thêm ActionListener cho menuList
        menuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = menuList.getSelectedValue();
                    if (selectedValue != null && selectedValue.equals("Item 1")) { 
                        // Mở form đã được code ở đây
                        JOptionPane.showMessageDialog(frame, "You clicked on Item 1");
                    }
                }
            }
        });
        
        // Thiết lập kích thước frame và hiển thị nó
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DangNhapView view = new DangNhapView();
                DangNhapModel model = new DangNhapModel();
                DangNhapController controller = new DangNhapController(view, model);
            }
        });
    }
}
