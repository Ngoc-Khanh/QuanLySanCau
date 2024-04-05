package PhieuDatHang.controller;

import static controller.DatabaseInfo.password;
import static controller.DatabaseInfo.url;
import static controller.DatabaseInfo.user;
import java.sql.Connection;
import java.sql.DriverManager;

import controller.DatabaseInfo;

public class ConnectDB implements DatabaseInfo {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("connect successfully");
		} catch (Exception e) {
			System.out.println("connect failure");
			e.printStackTrace();
		}
		return conn;
	}

}
