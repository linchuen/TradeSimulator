package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Config.HttpConfig;
import com.cooba.TradeSimulator.DataLayer.StockTradeRecordDB;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TWSEDataDownloadService.class, HttpConfig.class, ObjectMapper.class, HttpUtil.class})
class TWSEDataDownloadServiceTest {
    @Autowired
    TWSEDataDownloadService downloadService;

    @MockBean
    private StockTradeRecordDB stockTradeRecordDB;

    @Test
    void downloadData() throws IOException, CsvException {
        downloadService.downloadData("2330", LocalDate.now());
        Mockito.verify(stockTradeRecordDB).insertAll(ArgumentMatchers.anyList());
    }
}