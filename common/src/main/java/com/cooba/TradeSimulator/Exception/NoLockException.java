package com.cooba.TradeSimulator.Exception;

public class NoLockException extends Exception {

    @Override
    public String getMessage() {
        return "Can not get the lock";
    }
}
