package com.ft.springrestsecurity.service;

import com.ft.springrestsecurity.model.Employee;
import com.ft.springrestsecurity.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        if (employee.getId() != null) {
            employee.setId(null);
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee doesn't exits with id : " + employee.getId());
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee doesn't exits with id : " + employeeId);
        }
        employeeRepository.deleteById(employeeId);
    }
}
