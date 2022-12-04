package pro.sky.emlpoyee.service;

import org.springframework.stereotype.Service;
import pro.sky.emlpoyee.exception.EmployeeAlreadyAddedException;
import pro.sky.emlpoyee.exception.EmployeeNotFoundException;
import pro.sky.emlpoyee.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = new HashMap<>();
    }

    @Override
    public List<Employee> getEmployees() {
        return employees.values().stream().toList();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (this.employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой пользователь уже существуюет");
        }
        this.employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        return this.employees.remove(employee.getFullName());
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (this.employees.containsKey(employee.getFullName())) {
            return this.employees.get(employee.getFullName());
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }
}