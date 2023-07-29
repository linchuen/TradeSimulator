package com.cooba.TradeSimulator;

import com.cooba.TradeSimulator.Service.MyServiceImpl;
import com.cooba.TradeSimulator.service.StockReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TradeSystemApplicationTests {
    @Autowired
    MyServiceImpl myService;

    @Test
    void contextLoads() {
        StockReply stockReply = myService.receiveGreeting("2330");

        System.out.println(stockReply.toString());
    }

}
