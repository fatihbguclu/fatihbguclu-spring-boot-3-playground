package com.ft.springmvccrud.repository;

import com.ft.springmvccrud.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findAllByOrderByLastNameAsc();

}
