package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Student;

public class StudentDAOImpl implements StudentDAO {
	
	public static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String user = "DEVTESTER";
	public static final String password = "1234";
	private static Connection conn;

	@Override
	public Connection getConnection() {
		try {
			if (conn != null) {
				conn = DriverManager.getConnection(url, user, password);
			}
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createUser(Student s) {
		// TODO Auto-generated method stub
		conn = getConnection();
		String sql = "insert into student values(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stam = conn.prepareStatement(sql);
			
			stam.setString(1, s.getName());
			stam.setInt(2, s.getMajor());
			stam.setString(3, s.getAddress());
			stam.setString(4, s.getId());
			stam.setString(5, s.getPassword());
			stam.setString(6, s.getsNumber());
			stam.setString(7, s.getEmail());
			
			stam.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Student readUserFromId(String sId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student readUserFromSNumber(String sNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(String student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String sId) {
		// TODO Auto-generated method stub
		return false;
	}

}
