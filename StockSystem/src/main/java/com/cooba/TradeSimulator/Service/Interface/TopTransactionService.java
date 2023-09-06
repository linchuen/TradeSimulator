package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Entity.TopTransactionStock;
import com.cooba.TradeSimulator.Exception.DownloadException;

import java.util.List;

public interface TopTransactionService {
    List<TopTransactionStock> getTodayTopTransactionStock() throws DownloadException;
}
