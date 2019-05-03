package model;

import java.time.LocalDate;
import java.sql.Date;

public class Student {
	
	private String sId;
	private String sPw;
	private String sName;
	private int sMajor;
	private String sNumber;
	private LocalDate sBirthDate;
	
	public Student(String sId, String sPw) {
		this.sId = sId;
		this.sPw = sPw;
	}
	
	public LocalDate getsBirthDate() {
		return sBirthDate;
	}
	
	public String getsBirthDateToString() {
		if (sBirthDate != null) {
			int year = sBirthDate.getYear();
			int month = sBirthDate.getMonthValue();
			int day = sBirthDate.getDayOfMonth();
			return year + "-" + month + "-" + day;
		}
		return null;
		//return String.valueOf(sBirthDate);
	}

	public void setsBirthDate(LocalDate sBirthDate) {
		this.sBirthDate = sBirthDate;
	}
	
	// String format
	// YYYY-MM-DD
	public void setBirthDate(String birthDay) {
		String[] tokens = birthDay.split("-");
		try {
			int year = Integer.parseInt(tokens[0]);
			int month = Integer.parseInt(tokens[1]);
			int dayOfMonth = Integer.parseInt(tokens[2]);
			sBirthDate = LocalDate.of(year, month, dayOfMonth);	
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public Date getsBirthDateToDate() {
		if (sBirthDate != null) {
			return Date.valueOf(sBirthDate);
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		Student stu = new Student("abc", "1234");
//		stu.setBirthDate("2000-01-02");
//		System.out.println(stu.getsBirthDate());
//		
//		System.out.println(LocalDate.now());
//		
//		System.out.println(stu.getsBirthDateToString());
//		
//		System.out.println(stu.getsBirthDateToDate());
//	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getsPw() {
		return sPw;
	}

	public void setsPw(String sPw) {
		this.sPw = sPw;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public int getsMajor() {
		return sMajor;
	}

	public void setsMajor(int sMajor) {
		this.sMajor = sMajor;
	}

	public String getsNumber() {
		return sNumber;
	}

	public void setsNumber(String sNumber) {
		this.sNumber = sNumber;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sId == null) ? 0 : sId.hashCode());
		result = prime * result + ((sNumber == null) ? 0 : sNumber.hashCode());
		result = prime * result + ((sPw == null) ? 0 : sPw.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (sId == null) {
			if (other.sId != null)
				return false;
		} else if (!sId.equals(other.sId))
			return false;
		if (sNumber == null) {
			if (other.sNumber != null)
				return false;
		} else if (!sNumber.equals(other.sNumber))
			return false;
		if (sPw == null) {
			if (other.sPw != null)
				return false;
		} else if (!sPw.equals(other.sPw))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [sId=" + sId + ", sName=" + sName + ", sMajor=" + sMajor + ", sNumber=" + sNumber
				+ ", sBirthDate=" + sBirthDate + "]";
	}

	
	
	
	
	
	
}
