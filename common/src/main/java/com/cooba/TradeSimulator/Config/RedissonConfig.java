package com.cooba.TradeSimulator.Config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Value("${spring.redisson.address:redis://127.0.0.1:6379}")
    private String address;
    @Value("${spring.redisson.clientName:root}")
    private String clientName;
    @Value("${spring.redisson.password:password}")
    private String password;
    @Value("${spring.redisson.database:0}")
    private int database;
    @Value("${spring.redisson.enable}")
    private boolean enable;

    @Bean
    public RedissonClient redisson() {
        if (!enable) return null;

        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setClientName(clientName)
                .setPassword(password)
                .setDatabase(database);
        return Redisson.create(config);
    }
}
