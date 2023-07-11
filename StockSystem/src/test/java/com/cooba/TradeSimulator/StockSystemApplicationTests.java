package com.cooba.TradeSimulator;

import com.cooba.TradeSimulator.Service.StockInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class StockSystemApplicationTests {
	@Autowired
	StockInfoService stockInfoService;
	@Test
	void contextLoads() throws IOException {
		stockInfoService.crawlIndustry();
	}

}
