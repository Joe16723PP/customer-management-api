package com.playground.customermanagementapi.controllers;

import com.playground.customermanagementapi.dto.customer.CreateCustomerRequest;
import com.playground.customermanagementapi.dto.customer.UpdateCustomerRequest;
import com.playground.customermanagementapi.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    public ResponseEntity<?> getAllCustomers() {
        var resp = customerService.getAllCustomers();
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable("customerId") Long customerId) {
        var resp = customerService.getCustomer(customerId);
        return ResponseEntity.ok(resp);
    }

    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequest req) {
        try {
            customerService.createCustomer(req);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            // TODO: differentiate error code if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // TODO: for specific update, need to provide patch mapping
    @PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody UpdateCustomerRequest req) {
        try {
            // for case the spec need to provide id as params
            req.setCustomerId(customerId);
            customerService.updateCustomer(req);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            // TODO: differentiate error code if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
