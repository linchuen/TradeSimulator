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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

@Disabled
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("dev")
@ContextConfiguration(classes = {StockInfoService.class, StockInfoDataAccess.class})
class StockInfoServiceTest {

    @InjectMocks
    StockInfoService stockInfoService;

    @Mock
    StockInfoDataAccess stockInfoDataAccess;

    @Test
    void crawlIndustry() throws IOException {
        Mockito.doReturn(null).when(stockInfoDataAccess).saveAll(Mockito.anyList());
        stockInfoService.crawlIndustry();
    }
}