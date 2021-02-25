package native_jdbc_programming.dto;

public class Student {
	// fields
	private int stdNo;
	private String stdName;
	private int kor;
	private int eng;
	private int math;

	// Constructor
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int stdNo) {
		this.stdNo = stdNo;
	}

	public Student(int stdNo, String stdName, int kor, int eng, int math) {
		this.stdNo = stdNo;
		this.stdName = stdName;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	// getter & setter
	public int getStdNo() {
		return stdNo;
	}

	public void setStdNo(int stdNo) {
		this.stdNo = stdNo;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	// method

	public int total() {
		return kor + eng + math;
	}
	
	public double avg() {
		return total() / 3D;
	}

	@Override
	public String toString() {
		return String.format("Student [%s, %s, %s, %s, %s, %s, %s]", stdNo,
				stdName, kor, eng, math, total(), avg());
	}
	
	

}
