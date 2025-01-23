package com.playground.customermanagementapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "sales")
@Getter
@Setter
public class SaleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    private long saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false) // Define the foreign key column
    private CustomerEntity customer; // Reference to the Customer entity

    @Column(name = "sale_amount", nullable = false)
    private double saleAmount;

    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate;
}
