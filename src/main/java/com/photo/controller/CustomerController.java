package com.photo.controller;


import com.photo.entity.Customer;
import com.photo.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public Optional<Customer> findByPhone(@RequestParam String phone) {
        return customerRepository.findByPhone(phone);
    }

}