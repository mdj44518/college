package dao;

import java.sql.Connection;

import model.Student;

public interface StudentDAO {
	
	public Connection getConnection();
	public boolean createUser(Student s) ;
	public Student readUserFromId(String sId) ;
	public Student readUserFromSNumber(String sNumber) ;
	public boolean updateUser(String student);
	public boolean deleteUser(String sId);
}
