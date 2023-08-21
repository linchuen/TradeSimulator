package com.cooba.TradeSimulator.Channel;


import com.cooba.TradeSimulator.Object.stock.TradeStockInfo;
import com.cooba.TradeSimulator.service.StockReply;
import com.cooba.TradeSimulator.service.StockRequest;
import com.cooba.TradeSimulator.service.StockServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class GrpcClientStockService {

    @GrpcClient("stock-grpc-server")
    private StockServiceGrpc.StockServiceBlockingStub stockServiceBlockingStub;

    public TradeStockInfo getStockInfo(Integer id) {
        StockRequest request = StockRequest.newBuilder()
                .setStockId(id)
                .build();
        StockReply stockReply = stockServiceBlockingStub.getStockInfo(request);

        return TradeStockInfo.builder()
                .stockId(stockReply.getStockId())
                .stockcode(stockReply.getStockcode())
                .currentPrice(new BigDecimal(stockReply.getCurrentPrice()))
                .date(LocalDate.ofInstant(Instant.ofEpochSecond(stockReply.getDate()), ZoneId.systemDefault()))
                .build();
    }

}
