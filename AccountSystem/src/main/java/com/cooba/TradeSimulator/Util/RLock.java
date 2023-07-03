package com.cooba.TradeSimulator.Util;

import org.springframework.stereotype.Component;

@Component
public class RLock implements DistributedLock{
    @Override
    public DistributedLock getLock() {
        return this;
    }

    @Override
    public void lock() {

    }

    @Override
    public void tryLock() {

    }

    @Override
    public void unlock() {

    }
}
