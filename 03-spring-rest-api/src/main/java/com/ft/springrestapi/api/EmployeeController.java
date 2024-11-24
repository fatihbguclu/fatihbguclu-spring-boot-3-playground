package com.ft.springrestapi.api;

import com.ft.springrestapi.model.Employee;
import com.ft.springrestapi.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) {
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found with id : " + employeeId);
        }
        return optionalEmployee.get();
    }

}
