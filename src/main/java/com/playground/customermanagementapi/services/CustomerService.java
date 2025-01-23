package com.playground.customermanagementapi.services;

import com.playground.customermanagementapi.dto.customer.CreateCustomerRequest;
import com.playground.customermanagementapi.dto.customer.UpdateCustomerRequest;
import com.playground.customermanagementapi.entities.CustomerEntity;
import com.playground.customermanagementapi.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerEntity getCustomer(Long id) {
        return customerRepository.findActiveCustomerByCustomerId(id).orElse(null);
    }

    public void createCustomer(CreateCustomerRequest req) {
        // TODO: add proper validation
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(req.getFirstName());
        customerEntity.setLastName(req.getLastName());
        customerEntity.setCustomerDate(req.getCustomerDate());
        customerEntity.setVip(req.isVip());
        try {
            customerRepository.save(customerEntity);
        } catch (Exception e) {
            log.error("create customer failed", e);
            throw e;
        }
    }

    public void updateCustomer(UpdateCustomerRequest req) {
        // TODO: add proper validation
        CustomerEntity dbCustomer = getCustomer(req.getCustomerId());
        dbCustomer.setFirstName(req.getFirstName());
        dbCustomer.setLastName(req.getLastName());
        dbCustomer.setVip(req.isVip());
        dbCustomer.setCustomerDate(req.getCustomerDate());
        try {
            customerRepository.save(dbCustomer);
        } catch (Exception e) {
            log.error("update customer failed", e);
            throw e;
        }
    }


    // soft delete
    public boolean deleteCustomer(Long id) {
        int deleted = customerRepository.DeleteCustomerById(id);
        return deleted > 0;
    }
}
