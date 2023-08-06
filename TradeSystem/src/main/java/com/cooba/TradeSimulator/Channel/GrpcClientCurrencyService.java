package com.cooba.TradeSimulator.Channel;


import com.cooba.TradeSimulator.Object.CurrencyInfo;
import com.cooba.TradeSimulator.Object.TradeStockInfo;
import com.cooba.TradeSimulator.service.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class GrpcClientCurrencyService {

    @GrpcClient("currency-grpc-server")
    private CurrencyServiceGrpc.CurrencyServiceBlockingStub currencyServiceBlockingStub;

    public CurrencyInfo getCurrencyInfo(int currencyId) {
        CurrencyRequest request = CurrencyRequest.newBuilder()
                .setCurrencyId(currencyId)
                .build();
        CurrencyReply currencyReply = currencyServiceBlockingStub.getCurrencyInfo(request);

        return CurrencyInfo.builder()
                .currencyId(currencyReply.getCurrencyId())
                .name(currencyReply.getName())
                .exchangeRate(new BigDecimal(currencyReply.getExchangeRate()))
                .build();
    }

}
