package native_jdbc_programming.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import native_jdbc_programming.dto.Department;
import native_jdbc_programming.dto.Title;
import native_jdbc_programming.util.JdbcUtil;

public class TransactionService {
	public String transAddTitleAndDepartment(Title title, Department dept) {
		String titleSql = "insert into title values(?, ?)";
		String deptSql = "insert into department values(?, ?, ?)";
		Connection con = null;
		PreparedStatement tPstmt = null;
		PreparedStatement dPstmt = null;
		String res = null;

		try {
			con = JdbcUtil.getConnection();

			con.setAutoCommit(false);

			tPstmt = con.prepareStatement(titleSql);
			tPstmt.setInt(1, title.getTno());
			tPstmt.setString(2, title.getTname());
			tPstmt.executeUpdate();

			dPstmt = con.prepareStatement(deptSql);
			dPstmt.setInt(1, dept.getDeptno());
			dPstmt.setString(2, dept.getDeptname());
			dPstmt.setInt(3, dept.getFloor());
			dPstmt.executeUpdate();

			con.commit();
			res = "commit";

		} catch (SQLException e) {
			res = "rollback";
			try {
				con.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} finally {
			System.out.println(res);
			try {
				con.setAutoCommit(true);
				if (tPstmt != null) {
					tPstmt.close();
				}
				if (dPstmt != null) {
					dPstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}

		}
		return res;

	}// end of method Add

	public int transRemoveTitleAndDepartment(Title title, Department dept) {
		String tsql = "delete from title where tno = ?";
		String dsql = "delete from department where deptno = ?";
		Connection con = null;
		PreparedStatement tpstmt = null;
		PreparedStatement dpstmt = null;
		int res = 0;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);

			tpstmt = con.prepareStatement(tsql);
			tpstmt.setInt(1, title.getTno());
			res += tpstmt.executeUpdate();

			dpstmt = con.prepareStatement(dsql);
			dpstmt.setInt(1, dept.getDeptno());
			res += dpstmt.executeUpdate();

			if (res == 2) {
				con.commit();
				System.out.println("commit()");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			System.out.println(res);
			try {
				con.setAutoCommit(true);
				if (tpstmt != null) {
					tpstmt.close();
				}
				if (dpstmt != null) {
					dpstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}

		}
		return res;
	}

//	public void transAddTitleAndDepartment2(Title title, Department dept){
//		String res = null;
//		String tsql = "insert into title values(?, ?)";
//		String dsql = "insert into department values(?, ?, ?)";
//		try(Connection con = JdbcUtil.getConnection();
//				PreparedStatement tPstmt = con.prepareStatement(tsql);
//				PreparedStatement dPstmt = con.prepareStatement(dsql)){
//			
//			con.setAutoCommit(false);
//			
//			tPstmt.setInt(1, title.getTno());
//			tPstmt.setNString(2, title.getTname());
//			tPstmt.executeUpdate();
//			
//			dPstmt.setInt(1, dept.getDeptno());
//			dPstmt.setNString(2, dept.getDeptname());
//			dPstmt.setInt(3, dept.getFloor());
//			
//			con.commit();
//			res = "commit";
//			
//		} catch (SQLException e) {
//			res = "rollback";
//			try {
//				con.rollback();
//			}catch(SQLException e2){
//				
//			}
//			e.printStackTrace();
//		}
//	}
}// end of class
