package com.luiz.plugtime.service;

import com.luiz.plugtime.model.Employee;
import com.luiz.plugtime.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee createEmployee(Employee employee){
        return repository.save(employee);
    }
}
