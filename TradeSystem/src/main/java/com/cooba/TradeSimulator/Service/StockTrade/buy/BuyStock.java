package com.cooba.TradeSimulator.Service.StockTrade.buy;

import com.cooba.TradeSimulator.Object.TradeData;

public interface BuyStock {
    void prepare(TradeData tradeData);
    void prepareRollback(TradeData tradeData);
    void payMoney(TradeData tradeData);
    void payMoneyRollback(TradeData tradeData);
    void addStock(TradeData tradeData);
    void addStockRollback(TradeData tradeData);
}
