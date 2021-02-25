package native_jdbc_programming.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programming.dao.Impl.EmployeeDaoImpl;
import native_jdbc_programming.dto.Department;
import native_jdbc_programming.dto.Employee;
import native_jdbc_programming.dto.Title;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	
	private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

	@Test
	public void test01SelectEmployeeByAll() {
		System.out.printf("%s()%n", "test01SelectEmployeeByAll");
		List<Employee> list = dao.selectEmployeeByAll();
		Assert.assertNotNull(list);
		
		for(Employee e : list) {
			System.out.println(e);
		}
	
	}

	@Test
	public void test02SelectEmployeeByNo() {
		System.out.printf("%s()%n", "test05SelectEmployeeByNo");
		Employee emp = new Employee(4377);
		Employee searchEmp = dao.selectEmployeeByNo(emp);
		Assert.assertNotNull(searchEmp);
		System.out.println(searchEmp);
	}

	@Test
	public void test03InsertEmployee() {
		System.out.printf("%s()%n", "test03InsertEmployee");
		Employee newEmp = new Employee(1004, "천사", new Title(5), new Employee(4377), 2000000, new Department(1));
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.insertEmployee(newEmp));
		
	}

	@Test
	public void test04UpdateEmployee() {
		System.out.printf("%s()%n", "test04UpdateEmployee");
		Employee newEmp = new Employee(1004, "천사", new Title(5), new Employee(1004), 2000000, new Department(3));
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);
		System.out.println(dao.updateEmployee(newEmp));
	}

	@Test
	public void test05DeleteEmployee() {
		System.out.printf("%s()%n", "test05DeleteEmployee");
		int res = dao.deleteEmployee(1004);
		Assert.assertEquals(1, res);
		dao.selectEmployeeByAll().stream().forEach(System.out::println);
		
	}

}
