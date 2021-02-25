package native_jdbc_programming.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import native_jdbc_programming.dto.Department;
import native_jdbc_programming.dto.Employee;
import native_jdbc_programming.dto.Title;
import native_jdbc_programming.util.JdbcUtil;

public class EmployeeDaoImpl implements native_jdbc_programming.dao.EmployeeDao {

	private static EmployeeDaoImpl instance = new EmployeeDaoImpl();

	private EmployeeDaoImpl() {
	}

	public static EmployeeDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select empno,empname,title_no,title_name,manager_no, "
				+ "manager_name,salary,deptno,deptName, floor " + "from vw_full_employee"; // 전체검색하는 dml문을 문자열 변수에 저장
		try (Connection con = JdbcUtil.getConnection(); // 1. DB연결
				PreparedStatement pstmt = con.prepareStatement(sql); // 2. 쿼리 쏠 준비
				ResultSet rs = pstmt.executeQuery()) { // 쿼리 보내고 반환받은 결과값을 저장
			if (rs.next()) { // 포인터가 가르키는 곳 다음에 데이터가 있다면~
				List<Employee> list = new ArrayList<>(); // List 생성
				do {
					list.add(getEmployee(rs)); // ArrayList에 데이터를 추가
				} while (rs.next()); // 포인터가 가르키는 곳 다음에 데이터가 있다면 반복문 실행
				return list; // list 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empno = rs.getInt("empno");
		String empname = rs.getNString("empname");
		Title title = new Title(rs.getInt("title_no"));
		Employee manager = new Employee(rs.getInt("manager_no"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("deptno"));

		try {
			title.setTname(rs.getString("title_name"));
		} catch (SQLException e) {
		}

		try {
			manager.setEmpname(rs.getNString("manager_name"));
		} catch (SQLException e) {
		}

		try {
			dept.setDeptname(rs.getNString("deptname"));
		} catch (SQLException e) {
		}

		try {
			dept.setFloor(rs.getInt("floor"));
		} catch (SQLException e) {
		}

		return new Employee(empno, empname, title, manager, salary, dept);
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql = "select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptno,deptName, floor "
				+ "from vw_full_employee " + "where empno = ?"; // view테이블에서 검색
		try (Connection con = JdbcUtil.getConnection(); // 1. DB저장
				PreparedStatement pstmt = con.prepareStatement(sql)) { // 2. 쿼리보낼 준비
			pstmt.setInt(1, emp.getEmpno()); // 첫번째 물음표 자리에 사원번호를 반환하는 메소드 호출
			try (ResultSet rs = pstmt.executeQuery()) { // 위 문장을 실행 후 결과값(성공하면 1, 실패하면 0)을 저장
				if (rs.next()) { // 포인터가 가리키는 다음 데이터가 있으면~
					return getEmployee(rs); // 검색한 결과를 반환
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setNString(2, emp.getEmpname());
			pstmt.setInt(3, emp.getTitle().getTno());
			pstmt.setInt(4, emp.getManager().getEmpno());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getDeptno());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql = "update employee set deptno = ? where empno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getDept().getDeptno());
			pstmt.setInt(2, emp.getEmpno());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(int deptNo) {
		String sql = "delete from employee where empno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, deptNo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
