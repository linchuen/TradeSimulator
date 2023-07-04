package com.cooba.TradeSimulator.Entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class StockTradeInfo {
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
}