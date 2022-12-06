package pro.sky.emlpoyee.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.emlpoyee.exception.EmployeeAlreadyAddedException;
import pro.sky.emlpoyee.exception.EmployeeFullNameInvalidException;
import pro.sky.emlpoyee.exception.EmployeeNotFoundException;
import pro.sky.emlpoyee.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> employees;


    public EmployeeServiceImpl(Map<String, Employee> employees) {
        this.employees = employees;
    }


    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer salary, Integer departmentId) {
        if (checkString(firstName + lastName)) {
            throw new EmployeeFullNameInvalidException("Введены не допустимые символы");
        }
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, departmentId);
        if (this.employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой пользователь уже существуюет");
        }
        this.employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        if (checkString(firstName + lastName)) {
            throw new EmployeeFullNameInvalidException("");
        }
        Employee employee = findEmployee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName));
        return this.employees.remove(employee.getFullName());
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        if (checkString(firstName + lastName)) {
            throw new EmployeeFullNameInvalidException("");
        }
        Employee employee = employees.values().stream()
                .filter(e -> e.getFirstName().equalsIgnoreCase(firstName) && e.getLastName().equalsIgnoreCase(lastName))
                .findAny()
                .get();

        if (this.employees.containsKey(employee.getFullName())) {
            return this.employees.get(employee.getFullName());
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }
    @Override
    public Map<String,Employee> getEmployeeMap() {
        return this.employees;
    }

    private boolean checkString(String str) {
        return !StringUtils.isAlpha(str);
    }
}