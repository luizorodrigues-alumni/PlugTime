package com.luiz.plugtime.service;

import com.luiz.plugtime.dto.employee.EmployeeDetailDto;
import com.luiz.plugtime.exceptions.employee.EmployeeException;
import com.luiz.plugtime.model.Employee;
import com.luiz.plugtime.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Create a new employee
    public Employee createEmployee(Employee employee){
        try {
            return repository.save(employee);
        } catch (Exception e) {
            throw new EmployeeException(e.getMessage());
        }
    }

    // Get all Employees Detailed, with all fields.
    public List<EmployeeDetailDto> getDetailEmployees(){
        try{
            return repository.findAll().stream()
                    .map(employee -> new EmployeeDetailDto(
                            employee.getId(),
                            employee.getName(),
                            employee.getPhone(),
                            employee.getCpf(),
                            employee.getEmail()
                    )).toList();

        } catch (Exception e) {
            throw new EmployeeException(e.getMessage());
        }
    }
}
