package com.playground.customermanagementapi.repositories;

import com.playground.customermanagementapi.entities.SaleEntity;
import com.playground.customermanagementapi.repositories.queryprojector.FindTopSaleProjector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findAllByCustomerId(Long customerId);

    @Query(value = """
        WITH RankedSales AS (
            SELECT 
                customer_id,
                sale_amount,
                sale_date,
                RANK() OVER (PARTITION BY customer_id ORDER BY sale_amount DESC) AS rank
            FROM sales
            WHERE sale_date >= DATE_SUB(CURDATE(), INTERVAL :year YEAR) -- Only sales in the past year
        )
        SELECT 
            customer_id AS customerId,
            sale_amount AS saleAmount,
            sale_date AS saleDate,
            rank
        FROM RankedSales
        WHERE rank = 1 
        ORDER BY sale_amount DESC;
        """, nativeQuery = true)
    List<FindTopSaleProjector> findTopSalesForEachCustomer(@Param("year") int year);
}
