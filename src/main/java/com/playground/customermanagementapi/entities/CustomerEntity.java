package com.playground.customermanagementapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "customers")
@Getter
@Setter
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, unique = true, updatable = false)
    private Long customerId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "customer_date", nullable = false)
    private LocalDateTime customerDate;

    @Column(name = "is_vip")
    private boolean isVip = false;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

}
