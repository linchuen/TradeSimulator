package com.cooba.TradeSimulator.Entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
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