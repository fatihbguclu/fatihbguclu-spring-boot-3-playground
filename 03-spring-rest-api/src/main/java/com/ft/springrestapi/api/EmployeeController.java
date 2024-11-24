package com.ft.springrestapi.api;

import com.ft.springrestapi.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

}
