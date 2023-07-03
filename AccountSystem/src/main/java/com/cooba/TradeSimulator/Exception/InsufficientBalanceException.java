package com.cooba.TradeSimulator.Exception;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super("餘額不足\n" + message);
    }

    public InsufficientBalanceException() {
        super("餘額不足");
    }
}
