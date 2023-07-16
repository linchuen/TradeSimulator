package com.cooba.TradeSimulator;

import com.cooba.TradeSimulator.DataAccess.StockInfoDataLink;
import com.cooba.TradeSimulator.Service.StockInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(MockitoExtension.class)
class StockInfoServiceTest {

    @InjectMocks
    StockInfoService stockInfoService;

    @Mock
    StockInfoDataLink stockInfoDataLink;

    @Test
    void crawlIndustry() throws IOException {
        Mockito.doReturn(null).when(stockInfoDataLink).insertAll(anyList());

        stockInfoService.crawlIndustry();
        //assure that before insert data into database has no errors
        Mockito.verify(stockInfoDataLink).insertAll(anyList());
    }
}