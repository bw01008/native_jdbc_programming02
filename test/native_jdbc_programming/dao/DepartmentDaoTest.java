package native_jdbc_programming.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programming.dao.Impl.DepartmentDaoImpl;
import native_jdbc_programming.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();

	@Test
	public void test04SelectDepartmentByAll() {
		System.out.printf("%s()%n", "test04SelectDepartmentByAll");
		List<Department> DepartmentList = dao.selectDepartmentByAll();
		Assert.assertNotNull(DepartmentList);
		for(Department d : DepartmentList) {
			System.out.println(d);
		}

	}

	@Test
	public void test05SelectDepartmentByNo() {
		System.out.printf("%s()%n", "test05SelectDepartmentByNo");
		Department dept = new Department(1);
		Department searchDept = dao.selectDepartmentByNo(dept);
		Assert.assertNotNull(searchDept);
		System.out.println(searchDept);
	}

	@Test
	public void test01InsertDepartment() {
		System.out.printf("%s()%n", "testInsertDepartment");
		Department newDept = new Department(5, "마케팅", 3);
		int res = dao.insertDepartment(newDept);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDept));
	}

	@Test
	public void test02UpdateDepartmentDepartmentDao() {
		System.out.printf("%s()%n", "test02DeleteDepartmentDepartmentDao");
		Department newDept = new Department(5, "디자인", 7);
		int res = dao.updateDepartment(newDept);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDept));
	}

	@Test
	public void test03DeleteDepartmentInt() {
		System.out.printf("%s()%n", "test03DeleteDepartmentInt");
		int res = dao.deleteDepartment(5);
		Assert.assertEquals(1, res);
	}

}
