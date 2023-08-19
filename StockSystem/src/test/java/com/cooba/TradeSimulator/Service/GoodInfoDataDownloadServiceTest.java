package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.StockTradeRecordDataAccess;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GoodInfoDataDownloadServiceTest {
    @InjectMocks
    GoodInfoDataDownloadService downloadService;

    @Mock
    private StockTradeRecordDataAccess stockTradeRecordDataAccess;

    @Test
    void downloadData() throws IOException {
        LocalDate today = LocalDate.now();
        downloadService.downloadData("2330", today);
        Mockito.verify(stockTradeRecordDataAccess).insert(Mockito.any(StockTradeRecord.class));
    }
}