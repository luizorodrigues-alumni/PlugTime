package com.luiz.plugtime.controller;

import com.luiz.plugtime.dto.EmployeeDto;
import com.luiz.plugtime.model.Employee;
import com.luiz.plugtime.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        Employee newEmployee = new Employee();
        BeanUtils.copyProperties(employeeDto, newEmployee);
        service.createEmployee(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);

    }
}
