package com.cooba.TradeSimulator.DataLayer;

import java.math.BigDecimal;

public interface CurrencyDatabase {
    BigDecimal getRate(Integer currencyId);
}
