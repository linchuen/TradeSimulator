package com.cooba.TradeSimulator.Object;

@FunctionalInterface
public interface ThrowableRunnable {
    public abstract void run() throws Exception;
}
