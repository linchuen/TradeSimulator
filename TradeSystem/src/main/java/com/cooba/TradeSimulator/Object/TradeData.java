package com.cooba.TradeSimulator.Object;

import com.cooba.TradeSimulator.Object.currency.CurrencyInfo;
import com.cooba.TradeSimulator.Object.stock.TradeStockInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeData {
    private String billId;
    private Integer userId;
    private Integer stockId;
    private Integer currencyId;
    private TradeStockInfo tradeStockInfo;
    private BigDecimal amount;
    private CurrencyInfo currencyInfo;
    private BigDecimal price;
    private boolean isPaySuccess = false;
    private boolean isAddSuccess = false;
}
