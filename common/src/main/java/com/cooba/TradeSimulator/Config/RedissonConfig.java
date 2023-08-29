package com.cooba.TradeSimulator.Config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonConfig {
    @Value("${spring.redisson.enable}")
    private boolean enable;

    @Bean
    public RedissonClient redisson() {
        if (!enable) return null;
        return Redisson.create();
    }
}
