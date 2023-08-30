package com.cooba.TradeSimulator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@Disabled
@SpringBootTest
class CurrencySystemApplicationTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    Redisson redissonClient;

    @Test
    public void testCurrencySystem() {
        redisTemplate.opsForValue().set("test", "Hello2");
    }

    @Test
    public void test() throws InterruptedException {
        RLock lock = redissonClient.getLock("123");
        boolean getlock = lock.tryLock(10, TimeUnit.SECONDS);
        System.out.println(getlock);
        lock.unlock();
    }
}