package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Enum.DefaultCurrency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyDataAcccess implements CurrencyData{

    public BigDecimal getCurrencyRate(Integer currencyId){
        return DefaultCurrency.getCurrencyMap().get(currencyId);
    }
}
