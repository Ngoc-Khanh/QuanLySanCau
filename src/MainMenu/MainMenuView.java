package MainMenu;

import javax.swing.*;

import org.apache.poi.hslf.blip.DIB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import QuanLyDatSan.DatSanController;
import QuanLyDatSan.DatSanModel;
import QuanLyDatSan.DatSanView;
import View.ItemView;

public class MainMenuView extends JFrame {
    public SpringLayout layout;
    public JPanel panel;

    JButton btnDatSan;
    JButton btnDichVu;

    public JPanel menu() {
        // Gọi các buttons
        btnDatSan = new JButton("Quản lý đặt sân");
        btnDichVu = new JButton("Quản lý dịch vụ");
        
        // Set chiều dài cho các buttons
        Dimension btnSize = new Dimension(200, 50);
        btnDatSan.setPreferredSize(btnSize);
        btnDichVu.setPreferredSize(btnSize);
        
        Color backColor = new Color(0, 114, 60);
        Color foreColor = Color.white;
        Color borderColor = new Color(73, 105, 137);
        
        // Set màu button
        btnDatSan.setBackground(backColor);
        btnDichVu.setBackground(backColor);

        // Set màu chữ
        btnDatSan.setForeground(foreColor);
        btnDichVu.setForeground(foreColor);

        // Set viền buttons
        btnDatSan.setBorder(BorderFactory.createLineBorder(borderColor));
        btnDichVu.setBorder(BorderFactory.createLineBorder(borderColor));

        // Logo
        JLabel logo = new JLabel();
        ImageIcon originalImageIcon = new ImageIcon("D:\\code\\QuanLySanCau\\src\\img\\logo.jpg");
        Image originalImage = originalImageIcon.getImage();
        int scaledWidth = 200;
        int scaleHeight = 200;
        Image scaleImage = originalImage.getScaledInstance(scaledWidth, scaleHeight, Image.SCALE_SMOOTH);
        ImageIcon scaleImageIcon = new ImageIcon(scaleImage);
        logo.setIcon(scaleImageIcon);

        // Set căn lề trái cho văn bản trong các buttons
        btnDatSan.setHorizontalAlignment(SwingConstants.LEFT);
        btnDichVu.setHorizontalAlignment(SwingConstants.LEFT);

        layout = new SpringLayout();
        panel = new JPanel(layout);
        panel.setPreferredSize(new Dimension(200, 600));

        JPanel squarePanel = new JPanel();
        squarePanel.setPreferredSize(new Dimension(200, 1000));
        squarePanel.setBackground(backColor);

        panel.add(btnDatSan);
        panel.add(btnDichVu);
        panel.add(logo);
        panel.add(squarePanel);

        layout.putConstraint(SpringLayout.NORTH, btnDatSan, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnDatSan, 0, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, btnDichVu, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnDichVu, 0, SpringLayout.WEST, panel);

        setSize(1200, 700);
        setLocationRelativeTo(null);

        btnDatSan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DatSanView view = new DatSanView();
                DatSanModel model = new DatSanModel();
                DatSanController controller = new DatSanController(model, view);
                view.setVisible(true);
            }
        });

        btnDatSan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(ItemView::new);
            }
        });
        
        return panel;
    }
}
