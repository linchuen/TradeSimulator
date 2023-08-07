package com.cooba.TradeSimulator.Channel;


import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Object.asset.StockInfoAsset;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import com.cooba.TradeSimulator.service.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.math.BigDecimal;

@GrpcService
@AllArgsConstructor
public class GrpcAccountService extends AccountServiceGrpc.AccountServiceImplBase {
    private final WalletService walletService;

    public void minusMoney(MoneyRequest request, StreamObserver<Reply> responseObserver) {
        currencyChange(request, responseObserver, false);
    }

    public void addMoney(MoneyRequest request, StreamObserver<Reply> responseObserver) {
        currencyChange(request, responseObserver, true);
    }

    private void currencyChange(MoneyRequest request, StreamObserver<Reply> responseObserver, boolean isPlus) {
        int userId = request.getUserId();
        int currencyId = request.getCurrencyId();
        BigDecimal price = new BigDecimal(request.getPayPrice());
        CurrencyAsset currencyAsset = CurrencyAsset.builder()
                .currencyId(currencyId)
                .amount(price)
                .build();
        try {
            walletService.assetChange(userId, currencyAsset, isPlus);
            Reply reply = Reply.newBuilder()
                    .setIsSuccess(true)
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    public void addStock(StockTradeRequest request, StreamObserver<Reply> responseObserver) {
        stockChange(request, responseObserver, true);
    }

    public void minusStock(StockTradeRequest request, StreamObserver<Reply> responseObserver) {
        stockChange(request, responseObserver, false);
    }

    private void stockChange(StockTradeRequest request, StreamObserver<Reply> responseObserver, boolean isPlus) {
        int userId = request.getUserId();
        int stockId = request.getStockId();
        BigDecimal amount = new BigDecimal(request.getAmount());
        StockInfoAsset stockInfoAsset = StockInfoAsset.builder()
                .stockId(stockId)
                .amount(amount)
                .build();

        try {
            walletService.assetChange(userId, stockInfoAsset, isPlus);
            Reply reply = Reply.newBuilder()
                    .setIsSuccess(true)
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
