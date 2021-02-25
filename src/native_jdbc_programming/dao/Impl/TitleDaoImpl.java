package native_jdbc_programming.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import native_jdbc_programming.dao.TitleDao;
import native_jdbc_programming.dto.Title;
import native_jdbc_programming.util.JdbcUtil;

public class TitleDaoImpl implements TitleDao {
	// 싱글톤 객체 생성
	private static TitleDaoImpl instance = new TitleDaoImpl();

	private TitleDaoImpl() {
	}

	public static TitleDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select tno, tname from title";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Title> list = new ArrayList<>();
				do {
					list.add(getTitle(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int tno = rs.getInt("tno");
		String tname = rs.getString("tname");
		return new Title(tno, tname);
	}

	@Override
	public Title selectTitleByNo(Title title) {
		String sql = "select tno, tname from title where tno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, title.getTno());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getTitle(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertTitle(Title title) {
		String sql = "insert into title values(?, ?);";
		try (Connection con = JdbcUtil.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, title.getTno());
			pstmt.setNString(2, title.getTname());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateTitle(Title title) {
		String sql = "update title set tname = ? where tno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, title.getTname());
			pstmt.setInt(2, title.getTno());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteTitle(int tno) {
		String sql = "delete from title where tno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, tno);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
