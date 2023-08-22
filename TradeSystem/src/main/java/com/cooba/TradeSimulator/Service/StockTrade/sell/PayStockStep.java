package com.cooba.TradeSimulator.Service.StockTrade.sell;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Channel.GrpcClientAccountService;
import com.cooba.TradeSimulator.Object.Response;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import lombok.AllArgsConstructor;

@Step(transaction = "SellStock", sort = 2)
@AllArgsConstructor
public class PayStockStep extends TradeStep<TradeData> {
    private final GrpcClientAccountService grpcClientAccountService;

    @Override
    public void action(TradeData tradeData) {
        Response response =  grpcClientAccountService.minusStock(tradeData.getUserId(), tradeData.getTradeStockInfo().getStockId(), tradeData.getAmount());
        if (response.isSuccess()) {
            tradeData.setAddSuccess(true);
        } else {
            throw new RuntimeException(response.getErrorMsg());
        }
    }

    @Override
    public void rollback(TradeData tradeData) {
        if (tradeData.isPaySuccess()) {
            grpcClientAccountService.addStock(tradeData.getUserId(), tradeData.getTradeStockInfo().getStockId(), tradeData.getAmount());
        }
    }
}
