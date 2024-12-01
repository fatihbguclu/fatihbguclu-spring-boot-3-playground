package com.ft.springmvccrud.service;


import com.ft.springmvccrud.model.Employee;
import com.ft.springmvccrud.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        return result.orElseThrow(() -> new RuntimeException("Did not find employee id - " + theId));
    }

    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

}
