package com.cooba.TradeSimulator.Service.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;

@MapperScan("com.cooba.TradeSimulator.Mapper")
@ActiveProfiles(value = "test")
@TestConfiguration
public class Configration {
}
