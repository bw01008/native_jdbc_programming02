package native_jdbc_programming.dto;

public class Employee {
	private int empno;
	private String empname;
	private Title title;
	private Employee manager;
	private int salary;
	private Department dept;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(int empno) {
		this.empno = empno;
	}
	public Employee(int empno, String empname, Title title, Employee manager, int salary, Department dept) {
		this.empno = empno;
		this.empname = empname;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return String.format("Employee [empno=%s, empname=%s, title=%s, manager=%s, salary=%s, dept=%s]", empno,
				empname, title, manager, salary, dept);
	}
	
	
	
}
