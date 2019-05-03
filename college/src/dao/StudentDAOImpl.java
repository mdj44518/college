package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import model.Student;

public class StudentDAOImpl implements StudentDAO {
	
	public static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String user = "DEVTESTER";
	public static final String password = "1234";
//	private static String salt;
//	public StudentDAOImpl() {
//		salt = PasswordEncrypt.getSalt(16);
//	}

	private Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("데이터베이스 접근이 불가");
		}
		return null;
	}

	@Override
	public boolean createUser(Student student) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		if (con != null) {
			try {
				// student number 생성
				String sql = "select studentnumseq.nextval from dual";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				student.setsNumber(student.getsMajor() + "-" + rs.getInt(1));
				
				
				sql = "insert into student " + 
						"(sid, spw, sname, smajor, "
						+ "snumber, sBirthDate) values " + 
						"(?, ?, ?, ?, ?, ?)";
				
				PreparedStatement stmt = con.prepareCall(sql);
				stmt.setString(1, student.getsId());
				stmt.setString(2, student.getsPw());
				stmt.setString(3, student.getsName());
				stmt.setInt(4, student.getsMajor());
				stmt.setString(5, student.getsNumber());
				stmt.setDate(6, student.getsBirthDateToDate());
				
				int result = stmt.executeUpdate();
				
				if (result > 0) {
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("사용자 등록 실패. 다시 시도");
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("데이터베이스 연결 해제 오류");
					}
				}
			}
		}
		
//		String sql = "insert into student values(?, ?, ?, ?, ?, ?, ?)";
//		try {
//			PreparedStatement stam = conn.prepareStatement(sql);
//			
//			stam.setString(1, s.getName());
//			stam.setInt(2, s.getMajor());
//			stam.setString(3, s.getAddress());
//			stam.setString(4, s.getId());
//			stam.setString(5, s.getPassword());
//			stam.setString(6, s.getsNumber());
//			stam.setString(7, s.getEmail());
//			
//			stam.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
		Connection con = getConnection();
		if (con != null) {
			String[] tokens = student.split("/");
//			System.out.println(Arrays.toString(tokens));
			String sql = "UPDATE student " + 
					"set spw = ?, sname = ?, smajor = ? " + 
					"where sid = '" + tokens[0]+"'";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, tokens[1]);
				stmt.setString(2, tokens[2]);
				stmt.setInt(3, Integer.parseInt(tokens[3]));
				System.out.println("업뎃직전");
				int result = stmt.executeUpdate();
				System.out.println("업뎃후");
				if (result > 0) {
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						System.out.println("데이터베이스 연결해제 실패");
					}
				}
			}
			
		}
		return false;
	}

	@Override
	public boolean deleteUser(String sId) {
		Connection con = getConnection();
		if (con != null) {
			String sql = "delete from student " + 
					"where sid = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, sId);
				int result = stmt.executeUpdate();
				if (result ==1) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}

	@Override
	public boolean existsUserId(String sId) {
		Connection con = getConnection();
		
		if (con != null) {
			String sql = "select sId from student "
					+ "where sid = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, sId);
				
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("사용자 아이디 중복체크 오류");
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("데이터 베이스 닫기 실패");
					}
				}
			}
			
		}
		
		// defensive approach:
		// 
		return true;
	}

	@Override
	public boolean validateUser(String sId, String sPw) {
		//아디 패스워드 확인
		Connection con = getConnection();
		if (con != null) {
			String sql = "select sid " + 
					"from student " + 
					"where sid = ? and spw = ?";
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, sId);
				stmt.setString(2, sPw);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				System.out.println("사용자 로그인 처리 에러");
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		

		
		return false;
	}

	@Override
	public String returnList(String sId, String what) {
		Connection con = getConnection();
		if (con != null) {
			String sql = "select " + what +
					" from student " + 
					"where sid = '" + sId + "'";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
//				stmt.setString(1, what);
				ResultSet result = stmt.executeQuery(sql);
				if (result.next()) {
					return result.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

}
