package com.cooba.TradeSimulator.Service.StockTrade.sell;

import com.cooba.TradeSimulator.Object.TradeData;

public interface SellStock {
    void prepare(TradeData tradeData);
    void prepareRollback(TradeData tradeData);
    void payStock(TradeData tradeData);
    void payStockRollback(TradeData tradeData);
    void addMoney(TradeData tradeData);
    void addMoneyRollback(TradeData tradeData);
}
