package com.cooba.TradeSimulator.DataLayer;

import java.math.BigDecimal;

public interface CurrencyData {
    public BigDecimal getCurrencyRate(Integer currencyId);
}
