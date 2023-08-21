package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.StockTradeRecordDB;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class AnueDataDownloadServiceTest {
    @InjectMocks
    AnueDataDownloadService downloadService;

    @Mock
    private StockTradeRecordDB stockTradeRecordDB;

    @Test
    void downloadData() throws IOException {
        LocalDate today = LocalDate.now();
        downloadService.downloadData("2330", today);
        Mockito.verify(stockTradeRecordDB).insert(Mockito.any(StockTradeRecord.class));
    }
}