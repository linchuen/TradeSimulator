package com.cooba.TradeSimulator.Service;


import com.cooba.TradeSimulator.Enum.LockEnum;
import com.cooba.TradeSimulator.Service.Interface.LockService;
import lombok.Builder;
import lombok.Data;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
public class ReentrantLockService implements LockService {
    private final Map<String, ReentrantLock> reentrantLockMap = new ConcurrentHashMap<>();
    private final LinkedList<LockInfo> infoList = new LinkedList<>();
    private final int defaultTimeout = 30000;
    private final int MaxSize = 500;
    private final LockEnum lockEnum = LockEnum.Reentrant;

    @Override
    public Lock getLock(String key) {
        Lock lock = reentrantLockMap.get(key);
        if (lock != null) return lock;

        synchronized (infoList) {
            Lock synLock = reentrantLockMap.get(key);
            if (synLock != null) return synLock;

            removeExpiredLock();

            Lock resultLock = reentrantLockMap.putIfAbsent(key, new ReentrantLock());
            long releaseTime = System.currentTimeMillis() + defaultTimeout;
            infoList.add(LockInfo.builder().key(key).releaseTime(releaseTime).build());
            return resultLock;
        }
    }

    @Override
    public LockEnum getLockEnum() {
        return lockEnum;
    }

    private void removeExpiredLock() {
        if (infoList.size() > MaxSize) {
            for (LockInfo lockInfo : infoList) {
                if(lockInfo.getReleaseTime() >= System.currentTimeMillis()) continue;

                reentrantLockMap.remove(lockInfo.getKey());
                infoList.remove(lockInfo);
            }
        }
    }

    @Data
    @Builder
    private static class LockInfo {
        private String key;
        private long releaseTime;
    }
}
