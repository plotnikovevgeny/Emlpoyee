package pro.sky.emlpoyee.service;

import org.springframework.stereotype.Service;
import pro.sky.emlpoyee.model.Employee;
import pro.sky.emlpoyee.exception.EmployeeAlreadyAddedException;
import pro.sky.emlpoyee.exception.EmployeeNotFoundException;
import pro.sky.emlpoyee.exception.EmployeeStorageIsFullException;
@Service
public class EmployeeService {
    private Employee[] employees;

    public EmployeeService() {
        this(null);
    }

    public EmployeeService(Employee[] employees) {
        this.employees =  new Employee[2];
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Employee addEmployee(String firstName, String lastName) {
        if (isFull()) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }
        Employee tempEmployee = new Employee(firstName, lastName);
        if (isEmployeeDuplicate(tempEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой пользователь уже существуюет");
        }
        int index = findIndex();
        this.employees[index] = tempEmployee;
        return tempEmployee;
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        Employee tempEmployee = findEmployee(firstName, lastName);
        for (int i = 0; i < this.employees.length; i++) {
            if (this.employees[i].equals(tempEmployee)) {
                this.employees[i] = null;
                break;
            }
        }
        return tempEmployee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        boolean bool = false;
        Employee tempEmployee = new Employee(firstName, lastName);
        if (!isEmpty()) {
            for (Employee employee : this.employees) {
                if (employee != null && employee.equals(tempEmployee)) {
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

    private boolean isFull() {
        boolean full = true;
        for (Employee employee : this.employees) {
            if (employee == null) {
                full = false;
                break;
            }
        }
        return full;
    }

    private int findIndex() {
        int index = 0;
        for (int i = 0; i < this.employees.length; i++) {
            if (employees[i] == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean isEmployeeDuplicate(Employee employee) {
        boolean duplicate = false;
        if (!isEmpty()) {
            for (Employee tempEmployee : this.employees) {
                if (tempEmployee != null && tempEmployee.equals(employee)) {
                    duplicate = true;
                    break;
                }
            }
        }
        return duplicate;
    }

    private boolean isEmpty() {
        boolean empty = true;
        for (Employee employee : this.employees) {
            if (employee != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }
}
