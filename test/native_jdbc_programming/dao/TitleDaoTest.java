package native_jdbc_programming.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programming.dao.Impl.TitleDaoImpl;
import native_jdbc_programming.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {

	private static TitleDao dao = TitleDaoImpl.getInstance();

	@Test
	public void test01SelectTitleByAll() {
		System.out.printf("%s()%n", "testSelectTitleByAll");
		List<Title> titleList = dao.selectTitleByAll();
		Assert.assertNotNull(titleList);

		for (Title t : titleList) {
			System.out.println(t);
		}
	}

	@Test
	public void test02SelectTitleByNo() {
		System.out.printf("%s()%n", "testSelectTitleByNo");
		Title title = new Title(3);
		Title searchTitle = dao.selectTitleByNo(title);
		Assert.assertNotNull(searchTitle);
		System.out.println(searchTitle);
	}

	@Test
	public void test03InsertTitle() {
		System.out.printf("%s()%n", "testInsertTitle");	
		Title newTitle = new Title(6, "인턴");
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(newTitle));

	}

	@Test
	public void test04UpdateTitle() {
		System.out.printf("%s()%n", "testUpdateTitle");
		Title newTitle = new Title(6, "계약직");
		int res = dao.updateTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(newTitle));
	}

	@Test
	public void test05DeleteTitle() {
		System.out.printf("%s()%n", "testDeleteTitle");
		int res = dao.deleteTitle(6);
		Assert.assertEquals(1, res);
		dao.selectTitleByAll().stream().forEach(System.out::println);
	}

}// end of class
