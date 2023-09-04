package com.cooba.TradeSimulator.Service.Interface;

import java.math.BigDecimal;

public interface TradeService {
    String buy(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount);

    String sell(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount);
}
