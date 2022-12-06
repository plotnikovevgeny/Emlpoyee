package pro.sky.emlpoyee.service;

import pro.sky.emlpoyee.model.Employee;

import java.util.List;

public interface DepartmentService {
    Employee getEmployeeMaxSalary(Integer departmentId);
    Employee getEmployeeMinSalary(Integer departmentId);
    List<Employee> getAllEmployeeDepartment(Integer departmentId);

}
