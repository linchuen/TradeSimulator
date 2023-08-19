package com.cooba.TradeSimulator.Service.Interface;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.time.LocalDate;

public interface SkipDateService {

    void downloadData(int year) throws IOException, CsvException;

    boolean isSkipDate(LocalDate date);
}
