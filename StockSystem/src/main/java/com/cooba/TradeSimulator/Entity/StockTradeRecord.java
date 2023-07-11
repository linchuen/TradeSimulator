package com.cooba.TradeSimulator.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class StockTradeRecord {
    private Long id;
    private String stockcode;
    private LocalDate date;
    //成交股數
    private BigDecimal tradingVolume;
    //成交金額
    private BigDecimal transaction;
    private BigDecimal openingPrice;
    private BigDecimal highestPrice;
    private BigDecimal lowestPrice;
    private BigDecimal closingPrice;
    //成交筆數
    private BigDecimal turnover;
    private LocalDateTime createdTime;
}