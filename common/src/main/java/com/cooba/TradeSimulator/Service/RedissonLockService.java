package com.cooba.TradeSimulator.Service;


import com.cooba.TradeSimulator.Enum.LockEnum;
import com.cooba.TradeSimulator.Service.Interface.LockService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;

@Service
public class RedissonLockService implements LockService {
    @Autowired(required = false)
    private RedissonClient redissonClient;

    private final LockEnum lockEnum = LockEnum.Redis;

    @Override
    public Lock getLock(String key) {
        return redissonClient.getLock(key);
    }

    @Override
    public LockEnum getLockEnum() {
        return lockEnum;
    }
}
