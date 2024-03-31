/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLySanCau;

import java.sql.*;
/**
 *
 * @author Krug
 */
public class ConnectDB {
//    private static final String URL = "jdbc:mysql://localhost:3306/qlsancau";
//    private static final String USER = "root";
//    private static final String PASS = "";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = (Connection) DriverManager.getConnection(url, user, password);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlsancau", "root", "");
            System.out.println("Connected to the database");
        } catch (Exception e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        }
        return conn;
    }
    
//    public static void main(String[] args) {
//        // Kết nối đến cơ sở dữ liệu
//        Connection conn = ConnectDB.getConnection();
//        if (conn != null) {
//            try {
//                // Chuẩn bị câu lệnh truy vấn
//                String sql = "SELECT * FROM danhsachdatsan";
//                PreparedStatement preparedStatement = conn.prepareStatement(sql);
//
//                // Thực thi truy vấn và nhận kết quả
//                ResultSet rs = preparedStatement.executeQuery();
//
//                // In kết quả truy vấn
//                while (rs.next()) {
//                    System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3) + "  " + rs.getInt(4) + "  " + rs.getString(5)
//                    + "  " + rs.getDate(6) + "  " + rs.getDate(7));
//                }
//
//                // Đóng các tài nguyên
//                rs.close();
//                preparedStatement.close();
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Failed to establish connection to the database.");
//        }
//    }
}
