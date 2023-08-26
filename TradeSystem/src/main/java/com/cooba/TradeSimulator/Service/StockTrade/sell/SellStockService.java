package com.cooba.TradeSimulator.Service.StockTrade.sell;

import com.cooba.TradeSimulator.Channel.GrpcClientAccountService;
import com.cooba.TradeSimulator.Channel.GrpcClientCurrencyService;
import com.cooba.TradeSimulator.Channel.GrpcClientStockService;
import com.cooba.TradeSimulator.Object.Response;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.currency.CurrencyInfo;
import com.cooba.TradeSimulator.Object.stock.TradeStockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SellStockService implements SellStock{
    @Autowired
    private GrpcClientStockService grpcClientStockService;
    @Autowired
    private GrpcClientCurrencyService grpcClientCurrencyService;
    @Autowired
    private GrpcClientAccountService grpcClientAccountService;

    @Override
    public void prepare(TradeData tradeData) {
        TradeStockInfo tradeStockInfo = grpcClientStockService.getStockInfo(tradeData.getStockId());
        tradeData.setTradeStockInfo(tradeStockInfo);

        CurrencyInfo currency = grpcClientCurrencyService.getCurrencyInfo(tradeData.getCurrencyId());
        tradeData.setCurrencyInfo(currency);

        BigDecimal payPrice = tradeStockInfo.getCurrentPrice().divide(currency.getExchangeRate(), 5, RoundingMode.DOWN);
        tradeData.setPrice(payPrice);
        tradeData.setMoney(payPrice.multiply(tradeData.getAmount()));
    }

    @Override
    public void prepareRollback(TradeData tradeData) {

    }

    @Override
    public void payStock(TradeData tradeData) {
        Response response =  grpcClientAccountService.minusStock(tradeData.getUserId(), tradeData.getTradeStockInfo().getStockId(), tradeData.getAmount());
        if (response.isSuccess()) {
            tradeData.setAddSuccess(true);
        } else {
            throw new RuntimeException(response.getErrorMsg());
        }
    }

    @Override
    public void payStockRollback(TradeData tradeData) {
        if (tradeData.isPaySuccess()) {
            grpcClientAccountService.addStock(tradeData.getUserId(), tradeData.getTradeStockInfo().getStockId(), tradeData.getAmount());
        }
    }

    @Override
    public void addMoney(TradeData tradeData) {
        Response response =  grpcClientAccountService.addMoney(tradeData.getUserId(), tradeData.getCurrencyId(), tradeData.getPrice());
        if (response.isSuccess()) {
            tradeData.setPaySuccess(true);
        } else {
            throw new RuntimeException(response.getErrorMsg());
        }
    }

    @Override
    public void addMoneyRollback(TradeData tradeData) {
        if (tradeData.isAddSuccess()) {
            grpcClientAccountService.minusMoney(tradeData.getUserId(), tradeData.getCurrencyInfo().getCurrencyId(), tradeData.getPrice());
        }
    }
}
