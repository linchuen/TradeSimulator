package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Config.HttpConfig;
import com.cooba.TradeSimulator.DataLayer.TopTransactionDB;
import com.cooba.TradeSimulator.Util.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TWSETopTransactionService.class, HttpConfig.class, ObjectMapper.class, HttpUtil.class})
class TWSETopTransactionServiceTest {
    @Autowired
    TWSETopTransactionService twseTopTransactionService;

    @MockBean
    TopTransactionDB topTransactionDB;

    @Test
    void downloadData() throws IOException, CsvException {
        twseTopTransactionService.downloadData();
    }
}