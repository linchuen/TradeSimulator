package com.cooba.TradeSimulator.Util;

import com.cooba.TradeSimulator.Exception.NoLockException;
import com.cooba.TradeSimulator.Object.ThrowableRunnable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimulateLock {
    Map<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public void fakeSimulateRunning(String lockKey, int runningTime, int waitTime) throws InterruptedException, NoLockException {
        ReentrantLock lock = getLock(lockKey);

        if (lock.tryLock(waitTime, TimeUnit.MILLISECONDS)) {
            try {
                Thread.sleep(runningTime);
            } finally {
                lock.unlock();
            }
        } else {
            throw new NoLockException();
        }
    }

    public void simulateRunning(String lockKey, int waitTime, int timeout, Runnable runnable) throws InterruptedException, NoLockException {
        Lock lock = getLock(lockKey);
        if (lock.tryLock(waitTime, TimeUnit.MILLISECONDS)) {
            try {
                long releaseTime = System.currentTimeMillis() + timeout;
                runnable.run();
                if (System.currentTimeMillis() > releaseTime) {
                    System.out.println("Warning!! Running time is larger than timeout");
                }
            } finally {
                lock.unlock();
            }
        } else {
            throw new NoLockException();
        }
    }

    public ReentrantLock getLock(String lockKey) {
        lockMap.putIfAbsent(lockKey, new ReentrantLock());
        return lockMap.get(lockKey);
    }
}
