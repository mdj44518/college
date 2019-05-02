package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.Student;

public class StudentDAO2 {
	public static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String user = "DEVTESTER";
	public static final String password = "1234";
	private static Connection conn;
	
	public StudentDAO2() {
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(url, user, password);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(url, user, password);
			}
			
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean createUser(Student s) {
		
		return false;
	}
	
	public Student readUserFromId(String sId) {
		return null;
	}
	
	public Student readUserFromSNumber(String sNumber) {
		return null;
	}
}
