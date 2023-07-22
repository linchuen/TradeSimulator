package com.cooba.TradeSimulator.Mapper;

import java.util.List;

public interface CustStockInfoMapper {
    int insertAll(List<StockInfo> stockInfos);
    int update(StockInfo stockInfo);
    int updateAll(List<StockInfo> stockInfos);
    List<StockInfo> select(StockInfo stockInfo);
    StockInfo selectById(StockInfo stockInfo);
    int delete(StockInfo stockInfo);
}
