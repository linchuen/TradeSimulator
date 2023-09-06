package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Exception.DownloadException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface InfoService {
    List<StockInfo> findAllStockInfo() throws DownloadException;

    Optional<StockInfo> findStockInfo(int id);
}
