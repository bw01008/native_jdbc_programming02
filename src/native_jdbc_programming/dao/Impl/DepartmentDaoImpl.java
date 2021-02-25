package native_jdbc_programming.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import native_jdbc_programming.dao.DepartmentDao;
import native_jdbc_programming.dto.Department;
import native_jdbc_programming.util.JdbcUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	// 싱글톤

	private static DepartmentDaoImpl instance = new DepartmentDaoImpl();

	private DepartmentDaoImpl() {
	}

	public static DepartmentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Department> selectDepartmentByAll() {
		String sql = "select deptno, deptname, floor from department";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Department> list = new ArrayList<>();
				do {
					list.add(getDepartment(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptno = rs.getInt("deptNo");
		String deptname = rs.getString("deptName");
		int floor = rs.getInt("floor");
		return new Department(deptno, deptname, floor);
	}

	@Override
	public Department selectDepartmentByNo(Department dept) {
		String sql = "select deptno, deptname, floor from department where deptNo = ?";
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, dept.getDeptno());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getDepartment(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertDepartment(Department dept) {
		String sql = "insert into department values(?, ?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDeptname());
			pstmt.setInt(3, dept.getFloor());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateDepartment(Department dept) {
		String sql = "update department set deptname = ? where deptno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setNString(1, dept.getDeptname());
			pstmt.setInt(2, dept.getDeptno());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDepartment(int dept) {
		String sql = "delete from department where deptno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, dept);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
