package com.playground.customermanagementapi.services;

import com.playground.customermanagementapi.dto.sale.TopSalesDto;
import com.playground.customermanagementapi.entities.SaleEntity;
import com.playground.customermanagementapi.repositories.SaleRepository;
import com.playground.customermanagementapi.repositories.queryprojector.FindTopSaleProjector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;


    public SaleEntity save(SaleEntity saleEntity) {
        // TODO: add proper validation
        return saleRepository.save(saleEntity);
    }

    public List<SaleEntity> findAll() {
        return saleRepository.findAll();
    }

    public SaleEntity findById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        saleRepository.deleteById(id);
    }

    public List<SaleEntity> findByCustomerId(Long id) {
        return saleRepository.findAllByCustomerId(id);
    }

    public List<TopSalesDto> findTopSaleOfCustomersInPastYear(int year) {
        List<TopSalesDto> topSalesDtoList = new ArrayList<>();
        List<FindTopSaleProjector> topSales = saleRepository.findTopSalesForEachCustomer(year);
        for (FindTopSaleProjector topSale : topSales) {
            // TODO: can use builder if too complex
            TopSalesDto dto = new TopSalesDto();
            dto.setCustomerId(topSale.getCustomerId());
            dto.setSaleAmount(topSale.getSaleAmount());
            dto.setRank(topSale.getRank());
            dto.setSaleDate(topSale.getSaleDate());
            topSalesDtoList.add(dto);
        }
        return topSalesDtoList;
    }
}
