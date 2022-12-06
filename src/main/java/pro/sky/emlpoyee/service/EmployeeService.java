package pro.sky.emlpoyee.service;

import pro.sky.emlpoyee.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee addEmployee(String firstName, String lastName, Integer salary, Integer departmentId);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Map<String,Employee> getEmployeeMap();
}
