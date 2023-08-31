package com.cooba.TradeSimulator.Object;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyInfo {
    private Integer id;
    private String name;
    private BigDecimal rate;
}
