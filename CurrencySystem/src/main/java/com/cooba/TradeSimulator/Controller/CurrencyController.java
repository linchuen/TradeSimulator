package com.cooba.TradeSimulator.Controller;

import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.cooba.TradeSimulator.Service.Interface.CurrencyService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/{currencyId}")
    public Mono<ResponseEntity> createAccount(@PathVariable Integer currencyId) throws NotSupportCurrencyException, IOException, CsvException {
        return Mono.just(ResponseEntity.ok(currencyService.getCurrencyInfo(currencyId)));
    }
}
