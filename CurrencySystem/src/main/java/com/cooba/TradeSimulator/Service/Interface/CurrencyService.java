package com.cooba.TradeSimulator.Service.Interface;

import com.cooba.TradeSimulator.Entity.Currency;
import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public interface CurrencyService {
    void downloadCurrencyData() throws IOException, CsvException;

    Currency getCurrencyInfo(Integer currencyId) throws NotSupportCurrencyException, IOException, CsvException;
}
