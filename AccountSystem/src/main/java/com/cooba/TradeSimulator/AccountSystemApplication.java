package com.cooba.TradeSimulator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cooba.TradeSimulator.Mapper")
@SpringBootApplication
public class AccountSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountSystemApplication.class, args);
	}

}
