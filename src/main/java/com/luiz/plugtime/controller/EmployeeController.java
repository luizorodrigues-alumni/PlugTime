package com.luiz.plugtime.controller;

import com.luiz.plugtime.dto.employee.EmployeeCreateDto;
import com.luiz.plugtime.dto.employee.EmployeeDetailDto;
import com.luiz.plugtime.model.Employee;
import com.luiz.plugtime.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // Create Employee
    @PostMapping
    public ResponseEntity<EmployeeCreateDto> createEmployee(@RequestBody EmployeeCreateDto employeeDto){
        Employee newEmployee = new Employee();
        BeanUtils.copyProperties(employeeDto, newEmployee);
        Employee createdEmployee = service.createEmployee(newEmployee);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new EmployeeCreateDto(
                        createdEmployee.getName(),
                        createdEmployee.getPhone(),
                        createdEmployee.getCpf(),
                        createdEmployee.getEmail()
                ));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<EmployeeDetailDto>> getDetailEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getDetailEmployees());
    }
}
