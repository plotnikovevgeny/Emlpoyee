package pro.sky.emlpoyee.service;

import org.springframework.stereotype.Service;
import pro.sky.emlpoyee.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeMaxSalary(Integer departmentId) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();

    }

    @Override
    public Employee getEmployeeMinSalary(Integer departmentId) {
        return employeeService.getEmployees()
                .stream().filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .get();
    }

    @Override
    public List<Employee> getAllEmployeeDepartment(Integer departmentId) {
        return employeeService.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }
}