package com.cooba.TradeSimulator;

import com.cooba.TradeSimulator.DataAccess.StockInfoDataAccess;
import com.cooba.TradeSimulator.Service.StockInfoService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class StockInfoServiceTest {

    @InjectMocks
    StockInfoService stockInfoService;

    @Mock
    StockInfoDataAccess stockInfoDataAccess;

    @Test
    void crawlIndustry() throws IOException {
        Mockito.doReturn(null).when(stockInfoDataAccess).insertAll(Mockito.anyList());
        stockInfoService.crawlIndustry();
        //assure that before insert data into database has no errors
        Mockito.verify(stockInfoDataAccess).insertAll(Mockito.anyList());
    }
}