package com.cooba.TradeSimulator.Entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class StockInfo {
    private Long id;

    private String stockcode;

    private String name;

    private String isinCode;

    private LocalDate publishDate;

    private String marketType;

    private String industryType;

    private LocalDateTime updatedTime;
}