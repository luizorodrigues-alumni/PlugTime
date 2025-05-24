package com.luiz.plugtime.controller;

import com.luiz.plugtime.dto.CustomerDto;
import com.luiz.plugtime.model.Customer;
import com.luiz.plugtime.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // Creates a new Customer
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = service.createCustomer(customer);
        CustomerDto responseDto = new CustomerDto(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getPhone(),
                savedCustomer.getEmail()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // Retrieves the list containing public information about customers
    @GetMapping("/public")
    public ResponseEntity<List<CustomerDto>> getAllCustomersPublicInfo(){
        return ResponseEntity.status(200).body(service.getAllCustomersPublicInfo());
    }

    // Retrieves the list containing private information about customers
    @GetMapping("/admin")
    public ResponseEntity<List<Customer>> getAllCustomersFull(){
        return ResponseEntity.status(200).body(service.getAllCustomersFull());
    }

    @GetMapping("/public/{uuid}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID uuid){
        return ResponseEntity.status(200).body(service.getCustomerById(uuid));
    }
}
