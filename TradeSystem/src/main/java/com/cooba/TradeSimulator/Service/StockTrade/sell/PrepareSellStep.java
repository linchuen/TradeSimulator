package com.cooba.TradeSimulator.Service.StockTrade.sell;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Channel.GrpcClientCurrencyService;
import com.cooba.TradeSimulator.Channel.GrpcClientStockService;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import com.cooba.TradeSimulator.Object.currency.CurrencyInfo;
import com.cooba.TradeSimulator.Object.stock.TradeStockInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Step(transaction = "SellStock", sort = 1)
public class PrepareSellStep extends TradeStep<TradeData> {
    @Autowired
    private SellStock sellStock;

    @Override
    public void action(TradeData tradeData) {
        sellStock.prepare(tradeData);
    }

    @Override
    public void rollback(TradeData tradeData) {
        sellStock.prepareRollback(tradeData);
    }

}
