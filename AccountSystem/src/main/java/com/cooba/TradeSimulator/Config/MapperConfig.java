package com.cooba.TradeSimulator.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.cooba.TradeSimulator.Mapper")
public class MapperConfig {
}
