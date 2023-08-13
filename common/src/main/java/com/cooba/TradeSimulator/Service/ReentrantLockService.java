package com.cooba.TradeSimulator.Service;


import com.cooba.TradeSimulator.Enum.LockEnum;
import com.cooba.TradeSimulator.Service.Interface.LockService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ReentrantLockService implements LockService {
    private final Map<String, ReentrantLock> reentrantLockMap = new ConcurrentHashMap<>();

    private final LockEnum lockEnum = LockEnum.Reentrant;

    @Override
    public Lock getLock(String key) {
        Lock lock = reentrantLockMap.get(key);
        if (lock == null) {
            reentrantLockMap.putIfAbsent(key, new ReentrantLock());
        }
        return reentrantLockMap.get(key);
    }

    @Override
    public LockEnum getLockEnum() {
        return lockEnum;
    }
}
