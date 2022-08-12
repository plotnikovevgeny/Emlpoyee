package pro.sky.emlpoyee.service;

import org.springframework.stereotype.Service;
import pro.sky.emlpoyee.exception.EmployeeAlreadyAddedException;
import pro.sky.emlpoyee.exception.EmployeeNotFoundException;
import pro.sky.emlpoyee.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService() {
        this(null);
    }

    public EmployeeService(List<Employee> employees) {
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee addEmployee(String firstName, String lastName) {
        Employee tempEmployee = new Employee(firstName, lastName);
        if (isEmployeeDuplicate(tempEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой пользователь уже существуюет");
        }
        this.employees.add(tempEmployee);
        return tempEmployee;
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        Employee tempEmployee = findEmployee(firstName, lastName);
        this.employees.removeIf(employee -> employee.equals(tempEmployee));
        return tempEmployee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        boolean bool = false;
        Employee tempEmployee = new Employee(firstName, lastName);
        if (!this.employees.isEmpty()) {
            for (Employee employee : this.employees) {
                if (employee.equals(tempEmployee)) {
                    tempEmployee = employee;
                    bool = true;
                    break;
                }
            }
        }
        if (!bool) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return tempEmployee;
    }


    private boolean isEmployeeDuplicate(Employee employee) {
        boolean duplicate = false;
        if (!this.employees.isEmpty()) {
            for (Employee tempEmployee : this.employees) {
                if (tempEmployee != null && tempEmployee.equals(employee)) {
                    duplicate = true;
                    break;
                }
            }
        }
        return duplicate;
    }
}

