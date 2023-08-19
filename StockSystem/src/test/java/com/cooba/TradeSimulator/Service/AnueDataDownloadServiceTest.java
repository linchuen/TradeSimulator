package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.StockTradeRecordDataAccess;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnueDataDownloadServiceTest {
    @InjectMocks
    AnueDataDownloadService downloadService;

    @Mock
    private StockTradeRecordDataAccess stockTradeRecordDataAccess;
    @Mock
    private SkipDateService skipDateService;

    private final LocalDate today = LocalDate.now();


    @Test
    void ignoreDownloadData() throws IOException {
        set_is_skip_date(true);

        downloadService.downloadData("2330", today);
        Mockito.verify(stockTradeRecordDataAccess, Mockito.never()).insert(Mockito.any(StockTradeRecord.class));
    }

    @Test
    void downloadData() throws IOException {
        set_is_skip_date(false);

        downloadService.downloadData("2330", today);
        Mockito.verify(stockTradeRecordDataAccess).insert(Mockito.any(StockTradeRecord.class));
    }

    private void set_is_skip_date(boolean isSkipDate) {
        Mockito.when(skipDateService.isSkipDate(today)).thenReturn(isSkipDate);
    }
}