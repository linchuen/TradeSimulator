package com.cooba.TradeSimulator.Entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class StockTradeRecord {
    private Long id;
    private String stockcode;
    private LocalDate date;
    private int year;
    private int month;
    private BigDecimal tradingVolume;
    private BigDecimal transaction;
    private BigDecimal openingPrice;
    private BigDecimal highestPrice;
    private BigDecimal lowestPrice;
    private BigDecimal closingPrice;
    private BigDecimal turnover;
    private LocalDateTime createdTime;
}