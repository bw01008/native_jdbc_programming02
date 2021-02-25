package native_jdbc_programming.dto;

public class Title {
	//필드
	private int tno;
	private String tname;
	
	//생성자
	public Title() {
		// TODO Auto-generated constructor stub
	}
	
	public Title(int tno) {
		this.tno = tno;
	}

	public Title(int tno, String tname) {
		this.tno = tno;
		this.tname = tname;
	}
	
	// 메소드
	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return String.format("Title [tno=%s, tname=%s]", tno, tname);
	} 
	 
	 
	
	
	
	
	
}
