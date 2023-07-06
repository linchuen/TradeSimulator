package com.cooba.TradeSimulator.Service.Interface;

import java.time.LocalDate;

public interface StockDataDownloadService {

    void downloadData(String stockcode, LocalDate localDate) throws Exception;
}
