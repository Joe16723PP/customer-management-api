package com.playground.customermanagementapi.services;

import com.playground.customermanagementapi.entities.SaleEntity;
import com.playground.customermanagementapi.repositories.SaleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {
    @InjectMocks
    private SaleService saleService;

    @Mock
    private SaleRepository saleRepository;

    @Test
    public void testSaveSaleGivenValidRequestShouldNotReturnException() {
        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setCustomerId(1L);
        saleEntity.setSaleAmount(200.0);
        saleEntity.setSaleDate(LocalDateTime.now());

        // arrange
        when(saleRepository.save(saleEntity)).thenReturn(saleEntity);

        // ack
        SaleEntity savedEntity = saleService.save(saleEntity);

        // assert
        assertNotNull(savedEntity);
        verify(saleRepository, times(1)).save(saleEntity);
    }

}