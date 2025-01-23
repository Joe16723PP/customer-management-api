package com.playground.customermanagementapi.dto.customer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCustomerRequest {
    private String firstName;
    private String lastName;
    private LocalDateTime customerDate;
    private boolean isVip;
}
