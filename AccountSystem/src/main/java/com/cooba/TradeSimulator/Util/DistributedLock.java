package com.cooba.TradeSimulator.Util;

public interface DistributedLock {
    DistributedLock getLock();

    void lock();

    void tryLock();

    void unlock();
}
