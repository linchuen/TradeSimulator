package com.cooba.TradeSimulator.Mapper;

import com.cooba.TradeSimulator.Entity.StockInfo;

import java.util.List;

public interface CustStockInfoMapper {
    int insert(List<StockInfo> stockInfos);
    int update(StockInfo stockInfo);
    int updateAll(List<StockInfo> stockInfos);
    List<StockInfo> select(StockInfo stockInfo);
    StockInfo selectById(StockInfo stockInfo);
    int delete(StockInfo stockInfo);
}
