package com.playground.customermanagementapi.services;

import com.playground.customermanagementapi.entities.CustomerEntity;
import com.playground.customermanagementapi.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testGivenCustomerIdShouldReturnCustomer() {
        Long customerId = 1L;
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerId);

        // arrange
        when(customerRepository.findActiveCustomerByCustomerId(customerId)).thenReturn(Optional.of(customerEntity));
        // ack
        CustomerEntity dbCustomer = customerService.getCustomer(customerId);

        // assert
        assertEquals(dbCustomer.getCustomerId(), customerEntity.getCustomerId());
    }
}