package pro.sky.emlpoyee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.emlpoyee.model.Employee;
import pro.sky.emlpoyee.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeMaxSalary(@RequestParam Integer departmentId) {
        return departmentService.getEmployeeMaxSalary(departmentId);
    }
    @GetMapping("/min-salary")
    public Employee getEmployeeMinSalary(@RequestParam Integer departmentId) {
        return departmentService.getEmployeeMinSalary(departmentId);
    }@GetMapping("/all")
    public List<Employee> getAllEmployeeDepartment(@RequestParam Integer departmentId) {
        return departmentService.getAllEmployeeDepartment(departmentId);
    }
}
