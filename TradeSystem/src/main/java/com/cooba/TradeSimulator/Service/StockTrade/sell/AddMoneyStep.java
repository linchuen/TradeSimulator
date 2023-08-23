package com.cooba.TradeSimulator.Service.StockTrade.sell;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import org.springframework.beans.factory.annotation.Autowired;

@Step(transaction = "SellStock", sort = 3)
public class AddMoneyStep extends TradeStep<TradeData> {
    @Autowired
    private SellStock sellStock;

    @Override
    public void action(TradeData tradeData) {
        sellStock.addMoney(tradeData);
    }

    @Override
    public void rollback(TradeData tradeData) {
        sellStock.addMoneyRollback(tradeData);
    }
}
