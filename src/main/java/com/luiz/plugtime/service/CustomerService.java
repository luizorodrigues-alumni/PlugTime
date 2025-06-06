package com.luiz.plugtime.service;

import com.luiz.plugtime.dto.customer.CustomerDto;
import com.luiz.plugtime.exceptions.customer.CustomerCreationException;
import com.luiz.plugtime.exceptions.customer.CustomerException;
import com.luiz.plugtime.exceptions.customer.CustomerNotFoundException;
import com.luiz.plugtime.model.Customer;
import com.luiz.plugtime.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
                    .map(c -> new CustomerDto(c.getId(), c.getName(), c.getPhone(), c.getEmail()))
                    .toList();
        } catch (Exception e) {
            throw new CustomerException(e.getMessage());
        }
    }
    // Get customer By id
    public CustomerDto getCustomerById(UUID uuid){
        Customer customer = repository
                .findById(uuid)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not Found for id: " + uuid));

        return new CustomerDto(uuid, customer.getName(), customer.getPhone(), customer.getEmail());
    }
}
