/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows
 */
public class BookView extends JFrame {

    private JButton AddBtn, EditBtn, DeleteBtn, ClearBtn, SearchBtn;
    private JScrollPane JScrollPaneTable;
    private JTable Table;
    private JComboBox Sortcbb, Categorycbb;
    private JTextField SearchBooktf, BookNametf, Quantitytf, Pricetf, BookIDtf, Publishertf, Languagetf, Yeartf;
    private JLabel BookNamelbl, Pricelbl, Quantitylbl, Yearlbl, Categorylbl, Publisherlbl, Languagelbl;
    private DefaultTableModel tblModel;
    private final String[] columnNames = new String[]{
        "Mã sách", "Tên sách", "Ngôn ngữ", "Giá tiền", "Số lượng", "Năm xuất bản", "Thể loại", "Tác giả", "Nhà xuất bản"
    };
    private final Object data = new Object[][]{};

    public BookView() {
        initComponents();
    }

    private void initComponents() {
        JScrollPaneTable = new JScrollPane();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BookNamelbl = new JLabel("Tên sách");
        Languagelbl = new JLabel("Ngôn ngữ");
        Pricelbl = new JLabel("Giá");
        Quantitylbl = new JLabel("Số lượng");
        Yearlbl = new JLabel("Năm xuất bản");
        Categorylbl = new JLabel("Thể loại");
        Publisherlbl = new JLabel("Nhà xuất bản");
        

        BookNametf = new JTextField(10);
        SearchBooktf = new JTextField(15);
        Quantitytf = new JTextField(10);
        Pricetf = new JTextField(10);
        Pricetf.setText("0");
//        Authortf = new JTextField(50);
        BookIDtf = new JTextField(10);
        Publishertf = new JTextField(10);
        Languagetf = new JTextField(10);
        Yeartf = new JTextField(10);

        Categorycbb = new JComboBox<>(new String[]{"Giáo trình", "Tài liệu tham khảo", "Luận án", "Luận văn", "Sách bài tập"});
        Sortcbb = new JComboBox<>(new String[]{"Sắp xếp", "Tăng dần theo số trang", "Giảm dần theo số trang"});
        AddBtn = new JButton("Thêm");
        EditBtn = new JButton("Sửa");
        DeleteBtn = new JButton("Xoá");
        ClearBtn = new JButton("Nhập lại");
        SearchBtn = new JButton("Tìm kiếm");

        JScrollPaneTable = new JScrollPane();
        Table = new JTable();
        tblModel = new DefaultTableModel((Object[][]) data, columnNames);
        Table.setModel(tblModel);
        JScrollPaneTable.setViewportView(Table);
        JScrollPaneTable.setPreferredSize(new Dimension(1247, 300));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
         panel.setLayout(layout);
        panel.add(JScrollPaneTable);
        
       panel.add(BookNamelbl);
        panel.add(Pricelbl);
        panel.add(Quantitylbl);
        panel.add(Yearlbl);
        panel.add(Categorylbl);
//        panel.add(Authorlbl);
        panel.add(Publisherlbl);
        panel.add(Languagelbl);
        
        panel.add(BookNamelbl);
        panel.add(Pricelbl);
        panel.add(Quantitylbl);
        panel.add(Yearlbl);
        panel.add(Categorylbl);
        panel.add(Publisherlbl);
        panel.add(Languagelbl);
         panel.add(BookNametf);
        panel.add(Pricetf);
        panel.add(Quantitytf);
        panel.add(Yeartf);
        panel.add(Categorycbb);
//        panel.add(Authortf);
        panel.add(Publishertf);
        panel.add(Languagetf);
        
        panel.add(AddBtn);
        panel.add(EditBtn);
        panel.add(DeleteBtn);
        panel.add(ClearBtn);
        panel.add(SearchBooktf);
        panel.add(SearchBtn);
        panel.add(Sortcbb);
        //Bang sách
        
        
        layout.putConstraint(SpringLayout.WEST, Sortcbb, 315, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, Sortcbb, 20, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, SearchBooktf, 180, SpringLayout.WEST, Sortcbb);
        layout.putConstraint(SpringLayout.NORTH, SearchBooktf, 20, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, SearchBtn, 180, SpringLayout.WEST, SearchBooktf);
        layout.putConstraint(SpringLayout.NORTH, SearchBtn, 20, SpringLayout.NORTH, panel);
        
       layout.putConstraint(SpringLayout.WEST, JScrollPaneTable, 20, SpringLayout.WEST, panel);
         layout.putConstraint(SpringLayout.NORTH, JScrollPaneTable, 50, SpringLayout.NORTH, panel);
         layout.putConstraint(SpringLayout.EAST, JScrollPaneTable, -19, SpringLayout.EAST, panel);
         //cot 1
         layout.putConstraint(SpringLayout.WEST, BookNamelbl, 20, SpringLayout.WEST,panel );
         layout.putConstraint(SpringLayout.NORTH, BookNamelbl, 20, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, BookNametf, 90, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, BookNametf, 20, SpringLayout.SOUTH, JScrollPaneTable);
        
        layout.putConstraint(SpringLayout.WEST, Languagelbl, 20, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Languagelbl, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Languagetf, 90, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Languagetf, 60, SpringLayout.SOUTH, JScrollPaneTable);

       layout.putConstraint(SpringLayout.WEST, Pricelbl, 20, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Pricelbl, 100, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Pricetf, 90, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Pricetf, 100, SpringLayout.SOUTH, JScrollPaneTable);
        
        layout.putConstraint(SpringLayout.WEST, Quantitylbl, 20, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Quantitylbl, 140, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Quantitytf, 90, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Quantitytf, 140, SpringLayout.SOUTH, JScrollPaneTable);
        //cot2
         layout.putConstraint(SpringLayout.WEST, Categorylbl, 300, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Categorylbl, 20, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Categorycbb, 400, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Categorycbb, 20, SpringLayout.SOUTH, JScrollPaneTable);
        
        layout.putConstraint(SpringLayout.WEST, Publisherlbl, 300, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Publisherlbl, 60, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Publishertf, 400, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Publishertf, 60, SpringLayout.SOUTH, JScrollPaneTable);
        
        layout.putConstraint(SpringLayout.WEST, Yearlbl, 300, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Yearlbl, 100, SpringLayout.SOUTH, JScrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, Yeartf, 400, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, Yeartf, 100, SpringLayout.SOUTH, JScrollPaneTable);
        //nut
        layout.putConstraint(SpringLayout.EAST, AddBtn, -32, SpringLayout.WEST, EditBtn);
        layout.putConstraint(SpringLayout.NORTH, AddBtn, 25, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.EAST, EditBtn, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, EditBtn, 25, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.EAST, ClearBtn, -32, SpringLayout.WEST, DeleteBtn);
        layout.putConstraint(SpringLayout.NORTH, ClearBtn, 85, SpringLayout.SOUTH, JScrollPaneTable);

        layout.putConstraint(SpringLayout.EAST, DeleteBtn, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, DeleteBtn, 85, SpringLayout.SOUTH, JScrollPaneTable);
        add(panel);
        setTitle("Add Book");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookView::new);
    }
}
