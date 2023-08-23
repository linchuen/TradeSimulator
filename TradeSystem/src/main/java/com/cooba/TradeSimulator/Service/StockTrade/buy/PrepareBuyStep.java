package com.cooba.TradeSimulator.Service.StockTrade.buy;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import org.springframework.beans.factory.annotation.Autowired;

@Step(transaction = "BuyStock", sort = 1)
public class PrepareBuyStep extends TradeStep<TradeData> {
    @Autowired
    private BuyStock buyStock;

    @Override
    public void action(TradeData tradeData) {
        buyStock.prepare(tradeData);
    }

    @Override
    public void rollback(TradeData tradeData) {
        buyStock.prepareRollback(tradeData);
    }
}
