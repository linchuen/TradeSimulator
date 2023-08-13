package com.cooba.TradeSimulator.Service;


import com.cooba.TradeSimulator.Enum.LockEnum;
import com.cooba.TradeSimulator.Service.Interface.LockService;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ReentrantLockService implements LockService {
    private final Map<String, ReentrantLock> reentrantLockMap = new ConcurrentHashMap<>();
    private final TreeSet<String> treeSet = new TreeSet<>();
    private final int MaxSize = 500;
    private final LockEnum lockEnum = LockEnum.Reentrant;

    @Override
    public Lock getLock(String key) {
        Lock lock = reentrantLockMap.get(key);
        if (lock != null) return lock;

        synchronized (treeSet) {
            if (treeSet.size() > 500) {
                for (int i = 0; i < 250; i++) {
                    reentrantLockMap.remove(treeSet.first());
                    treeSet.remove(treeSet.first());
                }
            }
            reentrantLockMap.putIfAbsent(key, new ReentrantLock());
            treeSet.add(key);
        }
        return reentrantLockMap.get(key);
    }

    @Override
    public LockEnum getLockEnum() {
        return lockEnum;
    }
}
