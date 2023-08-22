package com.cooba.TradeSimulator.Service.StockTrade.buy;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Channel.GrpcClientAccountService;
import com.cooba.TradeSimulator.Object.Response;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import lombok.AllArgsConstructor;

@Step(transaction = "BuyStock", sort = 3)
@AllArgsConstructor
public class AddStockStep extends TradeStep<TradeData> {
    private final GrpcClientAccountService grpcClientAccountService;

    @Override
    public void action(TradeData tradeData) {
        Response response = grpcClientAccountService.addStock(tradeData.getUserId(), tradeData.getTradeStockInfo().getStockId(), tradeData.getAmount());
        if (response.isSuccess()) {
            tradeData.setAddSuccess(true);
        } else {
            throw new RuntimeException(response.getErrorMsg());
        }
    }

    @Override
    public void rollback(TradeData tradeData) {
        if (tradeData.isAddSuccess()) {
            grpcClientAccountService.minusStock(tradeData.getUserId(), tradeData.getTradeStockInfo().getStockId(), tradeData.getAmount());
        }
    }
}
