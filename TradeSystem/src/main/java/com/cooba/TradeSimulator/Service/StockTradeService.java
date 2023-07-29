package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Object.TradeStockInfo;
import com.cooba.TradeSimulator.Service.Interface.TradeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StockTradeService implements TradeService {

    public void buy(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount) {
        TradeStockInfo tradeStockInfo = getStockInfo(stockId);
        getCurrencyRate();
        payMoney();
        if (isPaySuccess()) {
            payBack();
        }
        logTradeRecord();
    }

    public void sell(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount) {

    }

    private TradeStockInfo getStockInfo(Integer stockId) {
        return TradeStockInfo.builder().build();
    }

    private void payMoney() {

    }

    private void getCurrencyRate() {

    }

    private boolean isPaySuccess() {
        return false;
    }

    private void payBack() {

    }

    private void logTradeRecord() {

    }
}
