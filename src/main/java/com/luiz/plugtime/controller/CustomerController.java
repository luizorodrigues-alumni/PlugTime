package com.luiz.plugtime.controller;

import com.luiz.plugtime.dto.CustomerDto;
import com.luiz.plugtime.model.Customer;
import com.luiz.plugtime.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = service.createCustomer(customer);
        CustomerDto responseDto = new CustomerDto(
                savedCustomer.getName(),
                savedCustomer.getPhone(),
                savedCustomer.getEmail()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
