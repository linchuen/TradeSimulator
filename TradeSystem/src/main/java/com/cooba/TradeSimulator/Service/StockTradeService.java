package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.UserTradeRecordDataAccess;
import com.cooba.TradeSimulator.Entity.UserTradeRecord;
import com.cooba.TradeSimulator.Object.CurrencyInfo;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStockInfo;
import com.cooba.TradeSimulator.Object.Transaction;
import com.cooba.TradeSimulator.Service.Interface.TradeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StockTradeService implements TradeService {
    private final Transaction<TradeData> buyStockTransaction;
    private final Transaction<TradeData> sellStockTransaction;
    private final UserTradeRecordDataAccess userTradeRecordDataAccess;

    public StockTradeService(@Qualifier("BuyStock") Transaction<TradeData> buyStockTransaction, @Qualifier("SellStock") Transaction<TradeData> sellStockTransaction, UserTradeRecordDataAccess userTradeRecordDataAccess) {
        this.buyStockTransaction = buyStockTransaction;
        this.sellStockTransaction = sellStockTransaction;
        this.userTradeRecordDataAccess = userTradeRecordDataAccess;
    }

    public void buy(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount) {
        String billId = UUID.randomUUID().toString();
        TradeData tradeData = new TradeData();
        tradeData.setBillId(billId);
        tradeData.setAmount(amount);

        try {
            logBeforeTrade(billId, userId, stockId, currencyId, amount);
            buyStockTransaction.startTransaction(tradeData);
            logAfterTrade(billId, userId, tradeData.getTradeStockInfo(), tradeData.getCurrencyInfo());
        } catch (Exception e) {
            logTradeError(userId, billId, e.getMessage());
        }
    }

    public void sell(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount) {
        String billId = UUID.randomUUID().toString();
        TradeData tradeData = new TradeData();
        tradeData.setBillId(billId);
        tradeData.setAmount(amount);

        try {
            logBeforeTrade(billId, userId, stockId, currencyId, amount.negate());
            sellStockTransaction.startTransaction(tradeData);
            logAfterTrade(billId, userId, tradeData.getTradeStockInfo(), tradeData.getCurrencyInfo());
        } catch (Exception e) {
            logTradeError(userId, billId, e.getMessage());
        }
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
        userTradeRecordDataAccess.insert(userTradeRecord);
    }

    private void logAfterTrade(String billId, Integer userId, TradeStockInfo tradeStockInfo, CurrencyInfo currency) {
        UserTradeRecord userTradeRecord = UserTradeRecord.builder()
                .billId(billId)
                .accountId(userId)
                .price(tradeStockInfo.getCurrentPrice())
                .stockDate(tradeStockInfo.getDate())
                .status(1)
                .updatedTime(LocalDateTime.now())
                .build();
        userTradeRecordDataAccess.update(userTradeRecord);
    }

    private void logTradeError(Integer userId, String billId, String errMsg) {
        UserTradeRecord userTradeRecord = UserTradeRecord.builder()
                .billId(billId)
                .accountId(userId)
                .status(-1)
                .errMsg(errMsg)
                .build();
        userTradeRecordDataAccess.update(userTradeRecord);
    }
}
