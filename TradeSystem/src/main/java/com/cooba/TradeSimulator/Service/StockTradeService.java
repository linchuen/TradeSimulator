package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.UserTradeRecordDataAccess;
import com.cooba.TradeSimulator.Entity.UserTradeRecord;
import com.cooba.TradeSimulator.Object.CurrencyInfo;
import com.cooba.TradeSimulator.Object.TradeStockInfo;
import com.cooba.TradeSimulator.Service.Interface.TradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StockTradeService implements TradeService {
    private final UserTradeRecordDataAccess userTradeRecordDataAccess;

    public void buy(Integer userId, String stockcode, Integer currencyId, BigDecimal amount) {
        String billId = UUID.randomUUID().toString();
        try {
            TradeStockInfo tradeStockInfo = getStockInfo(stockcode);
            CurrencyInfo currency = getCurrencyRate(currencyId);
            BigDecimal payPrice = tradeStockInfo.getCurrentPrice().divide(currency.getExchangeRate(), 5, RoundingMode.DOWN);

            logTradeRecord(billId, userId, tradeStockInfo, currency, amount, false);
            payMoney(userId, currencyId, payPrice);
            logTradeRecord(billId, userId, tradeStockInfo, currency, amount, true);
        } catch (Exception e) {
            if (isPaySuccess()) {
                payBack();
            }
            logTradeError(userId, billId, e.getMessage());
        }
    }

    public void sell(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount) {

    }

    private TradeStockInfo getStockInfo(String stockcode) {
        return TradeStockInfo.builder().build();
    }

    private void payMoney(Integer userId, Integer currencyId, BigDecimal amount) {

    }

    private CurrencyInfo getCurrencyRate(Integer currencyId) {
        return CurrencyInfo.builder().build();
    }

    private boolean isPaySuccess() {
        return false;
    }

    private void payBack() {

    }

    private void logTradeRecord(String billId, Integer userId, TradeStockInfo tradeStockInfo, CurrencyInfo currency, BigDecimal amount, boolean isCompleted) {
        UserTradeRecord userTradeRecord = UserTradeRecord.builder()
                .billId(billId)
                .accountId(userId)
                .currencyId(currency.getCurrencyId())
                .stockId(tradeStockInfo.getStockId())
                .price(tradeStockInfo.getCurrentPrice())
                .stockDate(tradeStockInfo.getDate())
                .amount(amount)
                .status(0)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
        userTradeRecordDataAccess.insert(userTradeRecord);
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
