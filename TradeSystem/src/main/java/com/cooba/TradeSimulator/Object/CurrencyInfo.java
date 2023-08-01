package com.cooba.TradeSimulator.Object;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Data
@SuperBuilder
public class CurrencyInfo extends Currency{
    private Integer currencyId;
    private BigDecimal exchangeRate;
}
