package com.playground.customermanagementapi.repositories;

import com.playground.customermanagementapi.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("select customers from customers where isDeleted = false and customerId = :customerId")
    Optional<CustomerEntity> findActiveCustomerByCustomerId(@Param("customerId") Long customerId);

    @Modifying
    @Query("update customers c set c.isDeleted = true where c.customerId = :customerId")
    int DeleteCustomerById(@Param("customerId") Long customerId);
}
