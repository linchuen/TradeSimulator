package com.cooba.TradeSimulator.Controller;

import com.cooba.TradeSimulator.Entity.StockInfo;
import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import com.cooba.TradeSimulator.Entity.TopTransactionStock;
import com.cooba.TradeSimulator.Exception.DownloadException;
import com.cooba.TradeSimulator.Exception.NotExistException;
import com.cooba.TradeSimulator.Service.Interface.InfoService;
import com.cooba.TradeSimulator.Service.Interface.StockDataService;
import com.cooba.TradeSimulator.Service.Interface.TopTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private InfoService stockInfoService;
    @Autowired
    private StockDataService stockDataService;
    @Autowired
    private TopTransactionService topTransactionService;

    @GetMapping("/info")
    public Mono<ResponseEntity> findAllStockInfo() throws DownloadException {
        return Mono.just(ResponseEntity.ok(stockInfoService.findAllStockInfo()));
    }

    @GetMapping("/info/{stockId}")
    public Mono<ResponseEntity<StockInfo>> findStockInfo(@PathVariable int stockId) throws NotExistException {
        Optional<StockInfo> stockInfoOptional = stockInfoService.findStockInfo(stockId);
        return stockInfoOptional.map(stockInfo -> Mono.just(ResponseEntity.ok(stockInfo)))
                .orElseThrow(NotExistException::new);
    }

    @GetMapping("/data/{stockcode}")
    public Mono<ResponseEntity> findStockInfo(@PathVariable String stockcode) throws Exception {
        StockTradeRecord stockTradeRecord = stockDataService.getTodayStockData(stockcode);
        return Mono.just(ResponseEntity.ok(stockTradeRecord));
    }

    @GetMapping("/top/transaction")
    public Mono<ResponseEntity> getTodayTopTransactionStock() throws Exception {
        List<TopTransactionStock> topTransactionStocks = topTransactionService.getTodayTopTransactionStock();
        return Mono.just(ResponseEntity.ok(topTransactionStocks));
    }
}
