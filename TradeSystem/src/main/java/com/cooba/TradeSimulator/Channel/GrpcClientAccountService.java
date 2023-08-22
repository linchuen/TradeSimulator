package com.cooba.TradeSimulator.Channel;


import com.cooba.TradeSimulator.Object.Response;
import com.cooba.TradeSimulator.Object.TransactionReply;
import com.cooba.TradeSimulator.service.AccountServiceGrpc;
import com.cooba.TradeSimulator.service.MoneyRequest;
import com.cooba.TradeSimulator.service.Reply;
import com.cooba.TradeSimulator.service.StockTradeRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GrpcClientAccountService {

    @GrpcClient("account-grpc-server")
    private AccountServiceGrpc.AccountServiceBlockingStub accountServiceBlockingStub;

    public Response minusMoney(Integer userId, Integer currencyId, BigDecimal payPrice) {
        MoneyRequest request = MoneyRequest.newBuilder()
                .setUserId(userId)
                .setCurrencyId(currencyId)
                .setPayPrice(payPrice.toPlainString())
                .build();
        Reply reply = accountServiceBlockingStub.minusMoney(request);

        return TransactionReply.builder().isSuccess(reply.getIsSuccess()).errorMsg(reply.getErrorMsg()).build();
    }

    public Response addMoney(Integer userId, Integer currencyId, BigDecimal payPrice) {
        MoneyRequest request = MoneyRequest.newBuilder()
                .setUserId(userId)
                .setCurrencyId(currencyId)
                .setPayPrice(payPrice.toPlainString())
                .build();
        Reply reply = accountServiceBlockingStub.addMoney(request);

        return TransactionReply.builder().isSuccess(reply.getIsSuccess()).errorMsg(reply.getErrorMsg()).build();
    }

    public Response minusStock(Integer userId, Integer stockId, BigDecimal amount){
        StockTradeRequest request = StockTradeRequest.newBuilder()
                .setUserId(userId)
                .setStockId(stockId)
                .setAmount(amount.toPlainString())
                .build();
        Reply reply = accountServiceBlockingStub.minusStock(request);

        return TransactionReply.builder().isSuccess(reply.getIsSuccess()).errorMsg(reply.getErrorMsg()).build();
    }

    public Response addStock(Integer userId, Integer stockId, BigDecimal amount){
        StockTradeRequest request = StockTradeRequest.newBuilder()
                .setUserId(userId)
                .setStockId(stockId)
                .setAmount(amount.toPlainString())
                .build();
        Reply reply = accountServiceBlockingStub.addStock(request);

        return TransactionReply.builder().isSuccess(reply.getIsSuccess()).errorMsg(reply.getErrorMsg()).build();
    }

}
