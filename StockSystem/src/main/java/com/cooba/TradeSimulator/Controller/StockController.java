package com.cooba.TradeSimulator.Controller;

import com.cooba.TradeSimulator.Service.Interface.SkipDateService;
import com.cooba.TradeSimulator.Service.Interface.StockDataService;
import com.cooba.TradeSimulator.Service.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private StockDataService stockDataService;
    @Autowired
    private SkipDateService skipDateService;
}
