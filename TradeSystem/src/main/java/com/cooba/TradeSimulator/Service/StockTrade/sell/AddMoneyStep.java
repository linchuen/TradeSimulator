package com.cooba.TradeSimulator.Service.StockTrade.sell;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Channel.GrpcClientAccountService;
import com.cooba.TradeSimulator.Object.Response;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import lombok.AllArgsConstructor;

@Step(transaction = "SellStock", sort = 3)
@AllArgsConstructor
public class AddMoneyStep extends TradeStep<TradeData> {
    private final GrpcClientAccountService grpcClientAccountService;

    @Override
    public void action(TradeData tradeData) {
        Response response =  grpcClientAccountService.addMoney(tradeData.getUserId(), tradeData.getCurrencyId(), tradeData.getPrice());
        if (response.isSuccess()) {
            tradeData.setPaySuccess(true);
        } else {
            throw new RuntimeException(response.getErrorMsg());
        }
    }

    @Override
    public void rollback(TradeData tradeData) {
        if (tradeData.isAddSuccess()) {
            grpcClientAccountService.minusMoney(tradeData.getUserId(), tradeData.getCurrencyInfo().getCurrencyId(), tradeData.getPrice());
        }
    }
}
