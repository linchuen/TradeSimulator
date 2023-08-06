package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Entity.StockTradeRecord;

public interface StockDataService {

    StockTradeRecord getTodayStockData(String stockcode) throws Exception;

    StockTradeRecord getTodayStockData(Integer stockId) throws Exception;
}
