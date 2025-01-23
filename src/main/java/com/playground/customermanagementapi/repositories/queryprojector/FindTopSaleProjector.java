package com.playground.customermanagementapi.repositories.queryprojector;

import java.time.LocalDateTime;

public interface FindTopSaleProjector {
    Long getCustomerId();
    Double getSaleAmount();
    LocalDateTime getSaleDate();
    Integer getRank();
}
