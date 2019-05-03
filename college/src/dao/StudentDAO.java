package dao;

import java.sql.Connection;

import model.Student;

public interface StudentDAO {
	
	//public Connection getConnection();
	public boolean createUser(Student student) ;
	public Student readUserFromId(String sId) ;
	public Student readUserFromSNumber(String sNumber) ;
	public boolean updateUser(String student);
	public boolean deleteUser(String sId);
	public boolean existsUserId(String sId);
	public boolean validateUser(String sId, String sPw);
	
	public String returnList(String sId,String what);
}
