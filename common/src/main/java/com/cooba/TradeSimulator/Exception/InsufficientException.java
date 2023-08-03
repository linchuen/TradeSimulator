package com.cooba.TradeSimulator.Exception;

public class InsufficientException extends Exception {
    public InsufficientException(String message) {
        super("不足錯誤\n" + message);
    }

    public InsufficientException() {
        super("不足錯誤");
    }
}
