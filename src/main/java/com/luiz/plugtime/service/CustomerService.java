package com.luiz.plugtime.service;

import com.luiz.plugtime.dto.CustomerDto;
import com.luiz.plugtime.exceptions.CustomerCreationException;
import com.luiz.plugtime.exceptions.CustomerException;
import com.luiz.plugtime.model.Customer;
import com.luiz.plugtime.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService (CustomerRepository repository){
        this.repository = repository;
    }

    // Create Customer
    public Customer createCustomer(Customer customer){
        try{
            return repository.save(customer);
        } catch (Exception e) {
            throw new CustomerCreationException(e.getMessage());
        }
    }

    // Return all customers info with restricted access
    public List<Customer> getAllCustomersFull(){
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new CustomerException(e.getMessage());
        }
    }

    // Return all customers public info with the DTO content
    public List<CustomerDto> getAllCustomersPublicInfo(){
        try {
            return repository.findAll().stream()
                    .map(c -> new CustomerDto(c.getName(), c.getPhone(), c.getEmail()))
                    .toList();
        } catch (Exception e) {
            throw new CustomerException(e.getMessage());
        }
    }
}
