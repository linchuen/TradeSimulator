package com.cooba.TradeSimulator.Service.StockTrade.sell;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Channel.GrpcClientAccountService;
import com.cooba.TradeSimulator.Object.Response;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Step(transaction = "SellStock", sort = 2)
public class PayStockStep extends TradeStep<TradeData> {
    @Autowired
    private SellStock sellStock;

    @Override
    public void action(TradeData tradeData) {
        sellStock.payStock(tradeData);
    }

    @Override
    public void rollback(TradeData tradeData) {
       sellStock.payStockRollback(tradeData);
    }
}
