package com.cooba.TradeSimulator.Service.StockTrade.sell;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Channel.GrpcClientAccountService;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Step(transaction = "SellStock", sort = 2)
@AllArgsConstructor
public class PayStockStep extends TradeStep<TradeData> {
    private final GrpcClientAccountService grpcClientAccountService;

    @Override
    public void action(TradeData tradeData) {
        boolean isPaySuccess = grpcClientAccountService.minusStock(tradeData.getUserId(), tradeData.getTradeStockInfo().getStockId(), tradeData.getAmount());
        tradeData.setPaySuccess(isPaySuccess);
    }

    @Override
    public void rollback(TradeData tradeData) {
        if (tradeData.isPaySuccess()) {
            grpcClientAccountService.addStock(tradeData.getUserId(), tradeData.getTradeStockInfo().getStockId(), tradeData.getAmount());
        }
    }
}
