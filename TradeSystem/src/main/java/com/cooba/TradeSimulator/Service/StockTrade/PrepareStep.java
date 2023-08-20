package com.cooba.TradeSimulator.Service.StockTrade;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Annotation.Steps;
import com.cooba.TradeSimulator.Channel.GrpcClientCurrencyService;
import com.cooba.TradeSimulator.Channel.GrpcClientStockService;
import com.cooba.TradeSimulator.Object.CurrencyInfo;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import com.cooba.TradeSimulator.Object.TradeStockInfo;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Steps(steps = {@Step(transaction = "BuyStock", sort = 1), @Step(transaction = "SellStock", sort = 1)})
@AllArgsConstructor
public class PrepareStep extends TradeStep<TradeData> {
    private final GrpcClientStockService grpcClientStockService;
    private final GrpcClientCurrencyService grpcClientCurrencyService;

    @Override
    public void action(TradeData tradeData) {
        TradeStockInfo tradeStockInfo = getStockInfo(tradeData.getStockId());
        tradeData.setTradeStockInfo(tradeStockInfo);

        CurrencyInfo currency = getCurrencyRate(tradeData.getCurrencyId());
        tradeData.setCurrencyInfo(currency);

        BigDecimal payPrice = tradeStockInfo.getCurrentPrice().divide(currency.getExchangeRate(), 5, RoundingMode.DOWN);
        tradeData.setPrice(payPrice);
    }

    @Override
    public void rollback(TradeData tradeData) {

    }

    private TradeStockInfo getStockInfo(Integer stockId) {
        return grpcClientStockService.getStockInfo(stockId);
    }

    private CurrencyInfo getCurrencyRate(Integer currencyId) {
        return grpcClientCurrencyService.getCurrencyInfo(currencyId);
    }
}
