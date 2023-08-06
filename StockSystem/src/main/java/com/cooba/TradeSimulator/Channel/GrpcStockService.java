package com.cooba.TradeSimulator.Channel;


import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Service.Interface.StockDataService;
import com.cooba.TradeSimulator.service.StockReply;
import com.cooba.TradeSimulator.service.StockRequest;
import com.cooba.TradeSimulator.service.StockServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
public class GrpcStockService extends StockServiceGrpc.StockServiceImplBase {
    private final StockDataService stockDataService;

    @Override
    public void getStockInfo(StockRequest request, StreamObserver<StockReply> responseObserver) {
        int stockId = request.getStockId();
        try {
            StockTradeRecord stockTradeRecord = stockDataService.getTodayStockData(stockId);
            StockReply reply = StockReply.newBuilder()
                    .setStockId(stockId)
                    .setStockcode(stockTradeRecord.getStockcode())
                    .setCurrentPrice(stockTradeRecord.getClosingPrice().toPlainString())
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

}
