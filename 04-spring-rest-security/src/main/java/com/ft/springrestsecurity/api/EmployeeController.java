package com.ft.springrestsecurity.api;

import com.ft.springrestsecurity.model.Employee;
import com.ft.springrestsecurity.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok("Deleted employee with id : " + employeeId);
    }
}
