package native_jdbc_programming.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programming.dao.Impl.StudentDaoImpl;
import native_jdbc_programming.dto.Student;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDaoTest {
	
	private static StudentDao dao = StudentDaoImpl.getInstance();

	@Test
	public void test01SelectStudentByAll() {
		System.out.printf("%s()%n", "test01SelectStudentByAll");
		List<Student> list = dao.selectStudentByAll();
		Assert.assertNotNull(list);
		for(Student l : list) {
			System.out.println(l);
		}
	}

	@Test
	public void test02SelectStudentByNo() {
		System.out.printf("%s()%n", "test02SelectStudentByNo");
		Student student = new Student(1);
		Student searchStudent = dao.selectStudentByNo(student);
		Assert.assertNotNull(searchStudent);
		System.out.println(dao.selectStudentByNo(student));
	}

	@Test
	public void test03InsertStudent() {
		System.out.printf("%s()%n", "test03InsertStudent");
		Student newStd = new Student(4, "우정아", 90, 70, 85);
		int res = dao.insertStudent(newStd);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectStudentByNo(newStd));
		
	}

	@Test
	public void test04UpdateStudent() {
		System.out.printf("%s()%n", "test04UpdateStudent");
		Student upStudent = new Student(4, "천사", 90, 80, 65);
		int res = dao.updateStudent(upStudent);
		Assert.assertEquals(1, res);
		System.out.println(dao.updateStudent(upStudent));
	}

	@Test
	public void test05DeleteStudent() {
		System.out.printf("%s()%n", "test05DeleteStudent");
		Student newStd = new Student(4);
		int res = dao.deleteStudent(newStd);
		Assert.assertEquals(1, res);
	}

}
