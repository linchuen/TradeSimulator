package com.cooba.TradeSimulator.Controller;

import com.cooba.TradeSimulator.Service.StockTradeService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    private StockTradeService stockTradeService;

    @PostMapping("buy")
    public Mono<ResponseEntity> buyStock(@RequestBody TradeInformation info) {
        stockTradeService.buy(info.userId, info.stockId, info.currencyId, info.amount);
        return Mono.just(ResponseEntity.ok().build());
    }

    @PostMapping("sell")
    public Mono<ResponseEntity> sellStock(@RequestBody TradeInformation info) {
        stockTradeService.sell(info.userId, info.stockId, info.currencyId, info.amount);
        return Mono.just(ResponseEntity.ok().build());
    }

    @Setter
    @Getter
    private static class TradeInformation {
        Integer userId;
        Integer stockId;
        Integer currencyId;
        BigDecimal amount;
    }
}
