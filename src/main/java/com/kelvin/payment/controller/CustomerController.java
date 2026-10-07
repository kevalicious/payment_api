package com.kelvin.payment.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.kelvin.payment.dto.CustomerRequest;
import com.kelvin.payment.dto.CustomerResponse;
import com.kelvin.payment.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	 private final CustomerService customerService;

	    public CustomerController(CustomerService customerService) {
	        this.customerService = customerService;
	    }

	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request) {
	        return customerService.createCustomer(request);
	    }

	    @GetMapping
	    public List<CustomerResponse> getAllCustomers() {
	        return customerService.getAllCustomers();
	    }

	    @GetMapping("/{id}")
	    public CustomerResponse getCustomerById(@PathVariable Long id) {
	        return customerService.getCustomerById(id);
	    }

}
