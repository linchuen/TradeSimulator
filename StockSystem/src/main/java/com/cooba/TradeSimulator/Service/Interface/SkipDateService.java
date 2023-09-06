package com.cooba.TradeSimulator.Service.Interface;

import java.time.LocalDate;

public interface SkipDateService {
    boolean isSkipDate(LocalDate date);
}
