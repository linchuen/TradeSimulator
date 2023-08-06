package com.cooba.TradeSimulator.Object;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CurrencyInfo extends Currency{
    private Integer currencyId;
    private BigDecimal exchangeRate;
}
