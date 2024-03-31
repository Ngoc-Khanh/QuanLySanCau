/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLySanCau;

import java.awt.event.ActionListener;
 import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
//import Database.ConnectDB;
import QuanLySanCau.SanCau;
import QuanLySanCau.SanCauFrm;
import javax.swing.SwingUtilities;
import javax.swing.text.View;

/**
 *
 * @author Krug
 */
public class SanCauDAO {
 private Connection conn;

    public SanCauDAO() {
    }
    
    public void setButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);
    }
    
    public List<SanCau> getSanCauList() {
        List<SanCau> sanCauList = new ArrayList<>();
        try (   Connection conn = ConnectDB.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM danhsachdatsan");
                ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                SanCau sc = new SanCau(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getString(5), rs.getDate(6), rs.getDate(7), rs.getTime(8), rs.getTime(9),
                        rs.getInt(10), rs.getBoolean(11), rs.getBoolean(12), rs.getBoolean(13), rs.getBoolean(14), rs.getBoolean(15),
                        rs.getBoolean(16), rs.getBoolean(17), rs.getFloat(18), rs.getBoolean(19)
                );
                sanCauList.add(sc);
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanCauList;
    }
    
    public boolean deleteItem(int MaDS) {
        conn = ConnectDB.getConnection();
        String sql = "DELETE FROM danhsachdatsan WHERE MaDS = ?";
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, MaDS); // Sử dụng setInt thay vì setFloat nếu MaDS là kiểu int
            int rowsAffected = st.executeUpdate();

            // Kiểm tra xem có hàng nào bị ảnh hưởng bởi lệnh DELETE hay không
            if (rowsAffected > 0) {
                // Nếu có, tức là xóa thành công, trả về true
                return true;
            } else {
                // Nếu không có hàng nào bị ảnh hưởng, tức là không có bản ghi nào được xóa, trả về false
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Trong trường hợp xảy ra lỗi, cũng trả về false
            return false;
        } finally {
            // Đóng các tài nguyên
            try {
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SanCauFrm::new);
    }
}
