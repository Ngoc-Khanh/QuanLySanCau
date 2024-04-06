package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.KhachHangView;
import controller.DatSanController;
import model.DatSanModel;

public class MainMenuView extends JFrame {
    public SpringLayout layout;
    public JPanel panel;

    JButton btnDatSan, btnDichVu, btnKhachHang, btnSan;

    public JPanel menu() {
        // Gọi các buttons
        btnDatSan = new JButton("Quản lý đặt sân");
        btnDichVu = new JButton("Quản lý dịch vụ");
        btnKhachHang = new JButton("Quản lý khách hàng");
        btnSan = new JButton("Quản lý sân");

        // Set chiều dài cho các buttons
        Dimension btnSize = new Dimension(200, 50);
        btnDatSan.setPreferredSize(btnSize);
        btnDichVu.setPreferredSize(btnSize);
        btnKhachHang.setPreferredSize(btnSize);
        btnSan.setPreferredSize(btnSize);

        Color backColor = new Color(0, 114, 60);
        Color foreColor = Color.white;
        Color borderColor = new Color(73, 105, 137);

        // Set màu button
        btnDatSan.setBackground(backColor);
        btnDichVu.setBackground(backColor);
        btnKhachHang.setBackground(backColor);
        btnSan.setBackground(backColor);

        // Set màu chữ
        btnDatSan.setForeground(foreColor);
        btnDichVu.setForeground(foreColor);
        btnKhachHang.setForeground(foreColor);
        btnSan.setForeground(foreColor);

        // Set viền buttons
        btnDatSan.setBorder(BorderFactory.createLineBorder(borderColor));
        btnDichVu.setBorder(BorderFactory.createLineBorder(borderColor));
        btnKhachHang.setBorder(BorderFactory.createLineBorder(borderColor));
        btnSan.setBorder(BorderFactory.createLineBorder(borderColor));

        // Logo
        JLabel logo = new JLabel();
        ImageIcon originalImageIcon = new ImageIcon(
                "C:\\Users\\Admin\\OneDrive - tuyenquang.edu.vn\\Study\\javas\\QuanLySanCau\\src\\img\\logo.jpg");
        Image originalImage = originalImageIcon.getImage();
        int scaledWidth = 200;
        int scaleHeight = 200;
        Image scaleImage = originalImage.getScaledInstance(scaledWidth, scaleHeight, Image.SCALE_SMOOTH);
        ImageIcon scaleImageIcon = new ImageIcon(scaleImage);
        logo.setIcon(scaleImageIcon);

        // Set căn lề trái cho văn bản trong các buttons
        btnDatSan.setHorizontalAlignment(SwingConstants.LEFT);
        btnDichVu.setHorizontalAlignment(SwingConstants.LEFT);
        btnKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
        btnSan.setHorizontalAlignment(SwingConstants.LEFT);

        layout = new SpringLayout();
        panel = new JPanel(layout);
        panel.setPreferredSize(new Dimension(200, 600));

        JPanel squarePanel = new JPanel();
        squarePanel.setPreferredSize(new Dimension(200, 1000));
        squarePanel.setBackground(backColor);

        panel.add(btnDatSan);
        panel.add(btnDichVu);
        panel.add(btnKhachHang);
        panel.add(btnSan);
        panel.add(logo);
        panel.add(squarePanel);

        layout.putConstraint(SpringLayout.NORTH, btnDatSan, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnDatSan, 0, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, btnDichVu, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnDichVu, 0, SpringLayout.WEST, panel);

        layout.putConstraint(SpringLayout.NORTH, btnKhachHang, 300, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnKhachHang, 0, SpringLayout.WEST, panel);
        
        layout.putConstraint(SpringLayout.NORTH, btnSan, 350, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnSan, 0, SpringLayout.WEST, panel);

        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(true);

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

        btnDichVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(ItemView::new);
            }
        });

        btnKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(KhachHangView::new);
            }
        });

        btnSan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(SanView::new);
            }
        });

        return panel;
    }
}
