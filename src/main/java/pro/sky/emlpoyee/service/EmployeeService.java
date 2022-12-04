package pro.sky.emlpoyee.service;

import pro.sky.emlpoyee.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee addEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

}
