package com.cooba.TradeSimulator.Object;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public abstract class Stock {
    private String stockcode;
    private BigDecimal currentPrice;
}
