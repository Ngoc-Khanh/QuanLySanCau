/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Krug
 */
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;

public class testBlank extends JFrame {
    public testBlank() {
        setTitle("Checkbox Table Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Dữ liệu mẫu
        Object[][] data = {
            {"Item 1", true},
            {"Item 2", false},
            {"Item 3", true},
            {"Item 4", false}
        };

        // Tên cột
        String[] columnNames = {"Name", "Select"};

        // Tạo bảng
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Tạo Renderer và Editor cho cột Select
        TableColumn selectColumn = table.getColumnModel().getColumn(1);
        selectColumn.setCellRenderer(new CheckBoxRenderer());
        selectColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        pack();
        setLocationRelativeTo(null);
    }

    // Renderer cho checkbox
    class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            setSelected((value != null && ((Boolean) value).booleanValue()));
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new testBlank().setVisible(true);
        });
    }
}