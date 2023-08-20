package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Channel.GrpcClientAccountService;
import com.cooba.TradeSimulator.Channel.GrpcClientCurrencyService;
import com.cooba.TradeSimulator.Channel.GrpcClientStockService;
import com.cooba.TradeSimulator.Config.TransactionInitConfig;
import com.cooba.TradeSimulator.DataLayer.UserTradeRecordDataAccess;
import com.cooba.TradeSimulator.Entity.UserTradeRecord;
import com.cooba.TradeSimulator.TestConfig.Configuration;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Optional;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.cooba.TradeSimulator.Service.StockTrade")
@ContextConfiguration(classes = {Configuration.class, TransactionInitConfig.class, StockTradeService.class})
class StockTradeServiceTest {
    @Autowired
    StockTradeService stockTradeService;
    @Autowired
    UserTradeRecordDataAccess userTradeRecordDataAccess;

    @MockBean
    GrpcClientAccountService grpcClientAccountService;
    @MockBean
    GrpcClientCurrencyService grpcClientCurrencyService;
    @MockBean
    GrpcClientStockService grpcClientStockService;

    @Test
    public void buy() {
        int userId = 1;
        int stockId = 1;
        int currencyId = 1;
        BigDecimal amount = BigDecimal.ONE;

        String billId = stockTradeService.buy(userId, stockId, currencyId, amount);
        Optional<UserTradeRecord> userTradeRecordOptional = userTradeRecordDataAccess.selectByBillId(billId);
        System.out.println(userTradeRecordOptional.get());
    }
}