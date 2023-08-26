package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.UserTradeRecordDB;
import com.cooba.TradeSimulator.Entity.UserTradeRecord;
import com.cooba.TradeSimulator.Object.currency.CurrencyInfo;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.stock.TradeStockInfo;
import com.cooba.TradeSimulator.Object.Transaction;
import com.cooba.TradeSimulator.Service.Interface.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class StockTradeService implements TradeService {
    @Autowired
    @Qualifier("BuyStock")
    private  Transaction<TradeData> buyStockTransaction;
    @Autowired
    @Qualifier("SellStock")
    private  Transaction<TradeData> sellStockTransaction;
    @Autowired
    private  UserTradeRecordDB userTradeRecordDB;

    public String buy(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount) {
        String billId = UUID.randomUUID().toString();
        TradeData tradeData = new TradeData();
        tradeData.setBillId(billId);
        tradeData.setAmount(amount);
        tradeData.setStockId(stockId);
        tradeData.setCurrencyId(currencyId);
        tradeData.setUserId(userId);

        try {
            logBeforeTrade(billId, userId, stockId, currencyId, amount);
            buyStockTransaction.startTransaction(tradeData);
            logAfterTrade(billId, userId, tradeData.getTradeStockInfo(), tradeData.getCurrencyInfo(), tradeData.getMoney());
        } catch (Exception e) {
            log.error("Error while: {}", e.getMessage());
            logTradeError(userId, billId, e.getMessage());
        }
        return billId;
    }

    public String sell(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount) {
        String billId = UUID.randomUUID().toString();
        TradeData tradeData = new TradeData();
        tradeData.setBillId(billId);
        tradeData.setAmount(amount);

        try {
            logBeforeTrade(billId, userId, stockId, currencyId, amount.negate());
            sellStockTransaction.startTransaction(tradeData);
            logAfterTrade(billId, userId, tradeData.getTradeStockInfo(), tradeData.getCurrencyInfo(), tradeData.getMoney());
        } catch (Exception e) {
            logTradeError(userId, billId, e.getMessage());
        }
        return billId;
    }

    private void logBeforeTrade(String billId, Integer userId, Integer stockId, Integer currencyId, BigDecimal amount) {
        UserTradeRecord userTradeRecord = UserTradeRecord.builder()
                .billId(billId)
                .accountId(userId)
                .currencyId(currencyId)
                .stockId(stockId)
                .amount(amount)
                .status(0)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
        userTradeRecordDB.insert(userTradeRecord);
    }

    private void logAfterTrade(String billId, Integer userId, TradeStockInfo tradeStockInfo, CurrencyInfo currency, BigDecimal money) {
        UserTradeRecord userTradeRecord = UserTradeRecord.builder()
                .billId(billId)
                .accountId(userId)
                .price(tradeStockInfo.getCurrentPrice())
                .money(money)
                .stockDate(tradeStockInfo.getDate())
                .status(1)
                .updatedTime(LocalDateTime.now())
                .build();
        userTradeRecordDB.update(userTradeRecord);
    }

    private void logTradeError(Integer userId, String billId, String errMsg) {
        UserTradeRecord userTradeRecord = UserTradeRecord.builder()
                .billId(billId)
                .accountId(userId)
                .status(-1)
                .errMsg(errMsg)
                .build();
        userTradeRecordDB.update(userTradeRecord);
    }
}
