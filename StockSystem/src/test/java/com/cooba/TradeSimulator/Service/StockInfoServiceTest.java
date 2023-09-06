package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Config.Configuration;
import com.cooba.TradeSimulator.Config.HttpConfig;
import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Exception.DownloadException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {Configuration.class, StockInfoService.class, HttpConfig.class, ObjectMapper.class})
class StockInfoServiceTest {
    @Autowired
    StockInfoService stockInfoService;

    @Test
    void crawlIndustry() throws DownloadException, IOException {
        stockInfoService.crawlIndustry();

        List<StockInfo> stockInfoList = stockInfoService.findAllStockInfo();
        for (StockInfo stockInfo : stockInfoList) {
            System.out.println(stockInfo);
            assertNotNull(stockInfo);
        }
    }
}