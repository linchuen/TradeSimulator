package com.cooba.TradeSimulator.Exception;

public class InsufficientStockException extends InsufficientException {
    public InsufficientStockException(String message) {
        super("股票數不足\n" + message);
    }

    public InsufficientStockException() {
        super("股票數不足");
    }
}
