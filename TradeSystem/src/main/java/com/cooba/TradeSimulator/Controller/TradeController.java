package com.cooba.TradeSimulator.Controller;

import com.cooba.TradeSimulator.Channel.GrpcClientCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {
    @Autowired
    GrpcClientCurrencyService grpcClientCurrencyService;

    @GetMapping("test")
    public void test(){
        System.out.println(grpcClientCurrencyService.getCurrencyInfo(1));
    }
}
