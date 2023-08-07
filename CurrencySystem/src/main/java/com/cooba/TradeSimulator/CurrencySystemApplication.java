package com.cooba.TradeSimulator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cooba.TradeSimulator.Mapper")
@SpringBootApplication
public class CurrencySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencySystemApplication.class, args);
    }

}
