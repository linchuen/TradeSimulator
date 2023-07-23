package com.cooba.TradeSimulator.Service.Interface;

public interface StockDataService {

    StockTradeRecord getTodayStockData(String stockcode) throws Exception;
}
