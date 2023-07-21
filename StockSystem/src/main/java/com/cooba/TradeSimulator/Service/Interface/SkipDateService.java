package com.cooba.TradeSimulator.Service.Interface;

import java.time.LocalDate;

public interface SkipDateService {

    void downloadData(int year) throws Exception;

    boolean isSkipDate(LocalDate date);
}
