package com.cooba.TradeSimulator.Object.stock;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class TradeStockInfo extends Stock {
    private Integer stockId;
    private LocalDate date;
}
