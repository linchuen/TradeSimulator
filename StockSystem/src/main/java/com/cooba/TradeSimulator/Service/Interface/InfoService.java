package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Entity.StockInfo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface InfoService {
    void crawlIndustry() throws IOException;

    List<StockInfo> findAllStockInfo() throws IOException;

    Optional<StockInfo> findStockInfo(int id);
}
