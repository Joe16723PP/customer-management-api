package com.playground.customermanagementapi.dto.sale;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopSalesDto {
    private Long customerId;
    private LocalDateTime saleDate;
    private Double saleAmount;
    private int rank;
}
