package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Entity.StockTradeRecord;

public interface StockDataService {

    StockTradeRecord getNowStockData(String stockcode);
}
