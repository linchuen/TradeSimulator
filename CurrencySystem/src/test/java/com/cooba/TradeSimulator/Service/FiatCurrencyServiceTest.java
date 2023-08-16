package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Config.HttpConfig;
import com.cooba.TradeSimulator.DataLayer.CurrencyDataAccess;
import com.cooba.TradeSimulator.Entity.Currency;
import com.cooba.TradeSimulator.Service.Config.Configuration;
import com.cooba.TradeSimulator.Service.Interface.CurrencyService;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {Configuration.class, FiatCurrencyService.class, HttpUtil.class, HttpConfig.class, ObjectMapper.class})
class FiatCurrencyServiceTest {
    @Autowired
    FiatCurrencyService currencyService;
    @Autowired
    CurrencyDataAccess currencyDataAccess;


    @Test
    void downloadCurrencyData() throws IOException, CsvException {
        currencyService.downloadCurrencyData();
        List<Currency> currencies = currencyDataAccess.selectAll();
        for (Currency currency : currencies) {
            System.out.println(currency);
            assertNotNull(currency.getRate());
        }
    }
}