package com.cooba.TradeSimulator.Channel;


import com.cooba.TradeSimulator.Entity.Currency;
import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.cooba.TradeSimulator.Service.Interface.CurrencyService;
import com.cooba.TradeSimulator.service.CurrencyReply;
import com.cooba.TradeSimulator.service.CurrencyRequest;
import com.cooba.TradeSimulator.service.CurrencyServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class GrpcCurrencyService extends CurrencyServiceGrpc.CurrencyServiceImplBase {
    private final CurrencyService currencyService;

    public void getCurrencyInfo(CurrencyRequest request, StreamObserver<CurrencyReply> responseObserver) {
        int currencyId = request.getCurrencyId();
        try {
            Currency currency = currencyService.getCurrencyInfo(currencyId);
            CurrencyReply reply = CurrencyReply.newBuilder()
                    .setCurrencyId(currency.getId())
                    .setName(currency.getName())
                    .setExchangeRate(currency.getRate().toPlainString())
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        } catch (Exception | NotSupportCurrencyException e) {
            responseObserver.onError(e);
        }
    }

}
