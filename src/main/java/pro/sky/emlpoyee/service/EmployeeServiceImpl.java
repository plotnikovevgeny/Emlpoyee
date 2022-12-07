package pro.sky.emlpoyee.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.emlpoyee.exception.EmployeeAlreadyAddedException;
import pro.sky.emlpoyee.exception.EmployeeFullNameInvalidException;
import pro.sky.emlpoyee.exception.EmployeeNotFoundException;
import pro.sky.emlpoyee.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }


    public List<Employee> getEmployees() {
        return new ArrayList<>(this.employees.values());
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer salary, Integer departmentId) {
        checkString(firstName + lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, departmentId);
        if (this.employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой пользователь уже существуюет");
        }
        this.employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        checkString(firstName + lastName);
        Employee employee = findEmployee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName));
        return this.employees.remove(employee.getFullName());
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        checkString(firstName + lastName);
        return employees.values().stream()
                .filter(e -> e.getFirstName().equalsIgnoreCase(firstName) && e.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Введены не допустимые символы"));
    }

    private void checkString(String str) {
        if (!StringUtils.isAlpha(str)) {
            throw new EmployeeFullNameInvalidException("Введены не допустимые символы");
        }
    }
}