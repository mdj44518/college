package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentRegisterApp {

	public static void main(String[] args) {
		// 1. 이름
		// 2. 전공
		// 3. 주소
		// 4. 아이디
		// 5. 비밀번호
		
		// 6. 학생번호 : 숫자로 된 고유한 값 (사용자가 정하는것이 아니고 내부적으로 발생되는 고유키)
		//    500-xxx
		// 7. email
		
		// 기능 (크러드 C,R,U,D)
		// 사용자 추가 (Create)
		// 사용자 조회 (Read)
		// 사용자 변경 (Update)
		// 사용자 삭제 (Delete)
		
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "DEVTESTER", "1234");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
