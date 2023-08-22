package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Channel.GrpcClientAccountService;
import com.cooba.TradeSimulator.Channel.GrpcClientCurrencyService;
import com.cooba.TradeSimulator.Channel.GrpcClientStockService;
import com.cooba.TradeSimulator.Config.TransactionInitConfig;
import com.cooba.TradeSimulator.DataLayer.UserTradeRecordDB;
import com.cooba.TradeSimulator.Entity.UserTradeRecord;
import com.cooba.TradeSimulator.Object.TransactionReply;
import com.cooba.TradeSimulator.Object.currency.CurrencyInfo;
import com.cooba.TradeSimulator.Object.stock.TradeStockInfo;
import com.cooba.TradeSimulator.TestConfig.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.cooba.TradeSimulator.Service.StockTrade")
@ContextConfiguration(classes = {Configuration.class, TransactionInitConfig.class, StockTradeService.class})
class StockTradeServiceTest {
    @Autowired
    StockTradeService stockTradeService;
    @Autowired
    UserTradeRecordDB userTradeRecordDB;

    @MockBean
    GrpcClientAccountService grpcClientAccountService;
    @MockBean
    GrpcClientCurrencyService grpcClientCurrencyService;
    @MockBean
    GrpcClientStockService grpcClientStockService;

    private final int userId = 1;
    private final int stockId = 1;
    private final int currencyId = 1;

    private void prepareTradeInfo(BigDecimal price, BigDecimal rate) {
        TradeStockInfo testTradeStockInfo = TradeStockInfo.builder()
                .stockId(stockId)
                .stockcode("2330")
                .currentPrice(price)
                .date(LocalDate.of(2023, 8, 21))
                .build();
        Mockito.when(grpcClientStockService.getStockInfo(anyInt())).thenReturn(testTradeStockInfo);

        CurrencyInfo testCurrency = CurrencyInfo.builder()
                .currencyId(currencyId)
                .exchangeRate(rate)
                .name("CNY")
                .build();
        Mockito.when(grpcClientCurrencyService.getCurrencyInfo(anyInt())).thenReturn(testCurrency);
    }

    private void payMoney(BigDecimal money, boolean isSuccess) {
        Mockito.when(grpcClientAccountService.minusMoney(userId, currencyId, money)).thenReturn(TransactionReply.builder().isSuccess(isSuccess).errorMsg("Error").build());
        Mockito.when(grpcClientAccountService.minusMoney(anyInt(), anyInt(), any(BigDecimal.class))).thenReturn(TransactionReply.builder().isSuccess(false).errorMsg("Error").build());
    }

    private void addStock(BigDecimal amount, boolean isSuccess) {
        Mockito.when(grpcClientAccountService.addStock(userId, stockId, amount)).thenReturn(TransactionReply.builder().isSuccess(isSuccess).errorMsg("Error").build());
        Mockito.when(grpcClientAccountService.addStock(anyInt(), anyInt(), any(BigDecimal.class))).thenReturn(TransactionReply.builder().isSuccess(false).errorMsg("Error").build());
    }

    @Test
    public void buySuccess() {
        BigDecimal amount = BigDecimal.valueOf(2);

        prepareTradeInfo(BigDecimal.valueOf(900), BigDecimal.valueOf(4.5));
        payMoney(BigDecimal.valueOf(400), true);
        addStock(BigDecimal.valueOf(2), true);

        String billId = stockTradeService.buy(userId, stockId, currencyId, amount);

        Optional<UserTradeRecord> userTradeRecordOptional = userTradeRecordDB.selectByBillId(billId);
        Assertions.assertTrue(userTradeRecordOptional.isPresent());
        UserTradeRecord result = userTradeRecordOptional.get();
        System.out.println("ErrMsg = " + result.getErrMsg());
        System.out.println("Price = " + result.getPrice());
        System.out.println("Amount = " + result.getAmount());
        System.out.println("Money = " + result.getMoney());
        Assertions.assertNull(result.getErrMsg());
        Assertions.assertEquals(0, result.getMoney().compareTo(BigDecimal.valueOf(400)));
    }

    @Test
    public void PayMoneyError() {
        BigDecimal amount = BigDecimal.valueOf(2);

        prepareTradeInfo(BigDecimal.valueOf(900), BigDecimal.valueOf(4.5));
        payMoney(BigDecimal.valueOf(400), false);
        addStock(BigDecimal.valueOf(2), true);

        String billId = stockTradeService.buy(userId, stockId, currencyId, amount);

        Optional<UserTradeRecord> userTradeRecordOptional = userTradeRecordDB.selectByBillId(billId);
        Assertions.assertTrue(userTradeRecordOptional.isPresent());
        UserTradeRecord result = userTradeRecordOptional.get();
        System.out.println("ErrMsg = " + result.getErrMsg());
        System.out.println("Price = " + result.getPrice());
        System.out.println("Amount = " + result.getAmount());
        System.out.println("Money = " + result.getMoney());
        Assertions.assertNull(result.getErrMsg());
        Assertions.assertEquals(0, result.getMoney().compareTo(BigDecimal.valueOf(400)));
    }
}