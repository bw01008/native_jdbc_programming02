package native_jdbc_programming.dao;

import java.util.List;

import native_jdbc_programming.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	
	Department selectDepartmentByNo(Department dept);
	
	int insertDepartment(Department dept);
	
	int updateDepartment(Department dept);
	
	int deleteDepartment(int dept);
	
}
