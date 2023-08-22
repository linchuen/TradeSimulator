package com.cooba.TradeSimulator.Service.StockTrade.buy;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Channel.GrpcClientAccountService;
import com.cooba.TradeSimulator.Object.Response;
import com.cooba.TradeSimulator.Object.TradeData;
import com.cooba.TradeSimulator.Object.TradeStep;
import lombok.AllArgsConstructor;

@Step(transaction = "BuyStock", sort = 2)
@AllArgsConstructor
public class PayMoneyStep extends TradeStep<TradeData> {
    private final GrpcClientAccountService grpcClientAccountService;

    @Override
    public void action(TradeData tradeData) {
        Response response = grpcClientAccountService.minusMoney(tradeData.getUserId(), tradeData.getCurrencyId(), tradeData.getMoney());
        if (response.isSuccess()) {
            tradeData.setPaySuccess(true);
        } else {
            throw new RuntimeException(response.getErrorMsg());
        }
    }

    @Override
    public void rollback(TradeData tradeData) {
        if (tradeData.isPaySuccess()) {
            grpcClientAccountService.addMoney(tradeData.getUserId(), tradeData.getCurrencyInfo().getCurrencyId(), tradeData.getPrice());
        }
    }
}
