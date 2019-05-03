package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.Student;
//1. 이름
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
public class StudentRegisterApp {
	
	static Scanner scan = new Scanner(System.in);
	static StudentDAO dao = new StudentDAOImpl();
	
	public static void main(String[] args) {
		
		char cmd = '0';
		while (cmd != 'q') {
			System.out.println("등록(r) / 삭제(d) / 변경(u) / 종료(q) ");
			cmd = Character.toLowerCase(scan.nextLine().charAt(0));
			
			switch (cmd) {
			case 'r':
				procRegister();
				break;
			case 'd':
				procDelete();
				break;
			case 'u':
				procChange();
				break;
			case 'q':
				break;
			}
		}

	}

	private static void procChange() {
		// 사용자 수정
		System.out.println("학생 변경");
		System.out.println("아이디: ");
		String sId = scan.nextLine().split("\\s+")[0];
		
		// student password
		System.out.print("비밀번호: ");
		String sPw = scan.nextLine().split("\\s+")[0];
		
		if (dao.validateUser(sId, sPw)) {
			String changePw = "";
			String changeName = "";
			String changeMajor = "";
			while (true) {
				System.out.print("변경할 비밀번호 입력 : ");
				String pw1 = scan.nextLine().split("\\s+")[0];
				System.out.print("동일하게 다시입력 :");
				String pw2 = scan.nextLine().split("\\s+")[0];
				if (pw1.equals(pw2)) {
					//맞고?????
					changePw = pw1;
					break;
				} else if (pw1.length() == 0 && pw2.length() == 0) {
					System.out.println("비번은 유지하고 넘어갑니다.");
					changePw = dao.returnList(sId, "sPw");
					break;
				} else {
					System.out.println("정확히 입력해주세요.");
				}
			}
			String now = dao.returnList(sId, "SNAME");
			System.out.print("변경할 이름(현재:"+ now +") : ");
			changeName = scan.nextLine().split("\\s+")[0];
			if (changeName.length() == 0) {
				changeName = now;
			}
			now = dao.returnList(sId, "smajor");
			System.out.println("변경할 전공(현재:"+ now +") : ");
			changeMajor = scan.nextLine().split("\\s+")[0];
			if (dao.updateUser(sId + "/" + changePw + "/" + changeName + "/" + changeMajor)) {
				System.out.println("변경 성공");
			} else {
				System.out.println("변경 실패");
			}
		}
	}

	private static void procDelete() {
		System.out.println("학생 삭제");
		System.out.println("아이디: ");
		String sId = scan.nextLine().split("\\s+")[0];
		
		// student password
		System.out.print("비밀번호: ");
		String sPw = scan.nextLine().split("\\s+")[0];
		
		if (dao.validateUser(sId, sPw)) {
			if (dao.deleteUser(sId)) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제가 실패");
			}
		} else {
			System.out.println("로그인 실패. 다시 시도");
		}
		
	}

	private static void procRegister() {
		Student st = new Student("", "");
		System.out.println("등록 정보를 입력");
		
		// student id
		while (true) {
			System.out.print("아이디: ");
			String sId = scan.nextLine().split("\\s+")[0];
			if (!dao.existsUserId(sId)) {
				st.setsId(sId);
				break;
			}
			System.out.println("아이디가 중복. 다시입력");
		}
		
		// student password
		System.out.print("비밀번호: ");
		st.setsPw(scan.nextLine().split("\\s+")[0]);
		
		// student name
		System.out.print("이름: ");
		st.setsName(scan.nextLine());
		
		// student major
		while (true) {
			System.out.print("전공: ");
			try {
				int sMajor = Integer.parseInt(scan.nextLine().split("\\s+")[0]);
				st.setsMajor(sMajor);
				//100 200 이런게 아니면? 어디서 판독하지?;
				break;
			} catch (Exception e) {
				System.out.println("잘못입력. 다시입력하세요");
			}
		}
		
		
		while (true) {
			System.out.print("생년월일(YYYY-MM-DD: ");
			String birthStr = scan.nextLine().split("\\s+")[0];
			String[] tokens = birthStr.split("-");
			
			if (tokens.length == 3) {
				try {
					for (String i : tokens) {
						Integer.parseInt(i);
					}
					st.setBirthDate(birthStr);
					break;
				} catch (Exception e) {
					System.out.println("잘못입력. 다시 입력해주세요");
				}
			} else {
				System.out.println("잘못입력. 다시 입력해주세요");
			}
		}
		
		if (dao.createUser(st)) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패 다시 시도");
		}
		
	}

}
