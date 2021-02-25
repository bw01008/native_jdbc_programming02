package native_jdbc_programming.dao;

import java.util.List;

import native_jdbc_programming.dto.Title;

public interface TitleDao {

	public List<Title> selectTitleByAll();
	
	public Title selectTitleByNo(Title title);
	
	public int insertTitle(Title title);
	
	public int updateTitle(Title title);
	
	public int deleteTitle(int tno);
	
}
