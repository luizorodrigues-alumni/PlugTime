package com.luiz.plugtime.service;

import com.luiz.plugtime.model.Customer;
import com.luiz.plugtime.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository repository;

    public CustomerService (CustomerRepository repository){
        this.repository = repository;
    }

    public Customer createCustomer(Customer customer){
        return repository.save(customer);
    }
}
