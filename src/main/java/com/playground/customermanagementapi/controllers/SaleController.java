package com.playground.customermanagementapi.controllers;

import com.playground.customermanagementapi.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping()
    public ResponseEntity<?> getAllSales() {
        var sales = saleService.findAll();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/top-sale")
    public ResponseEntity<?> getSaleById(@RequestParam(value = "yearInterval", defaultValue = "1") int yearInterval) {
        var sales = saleService.findTopSaleOfCustomersInPastYear(yearInterval);
        return ResponseEntity.ok(sales);
    }
}
