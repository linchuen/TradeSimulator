package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Config.HttpConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {Configuration.class, FiatCurrencyService.class, HttpConfig.class, ObjectMapper.class})
class StockInfoServiceTest {

    @Test
    void crawlIndustry() {
    }
}