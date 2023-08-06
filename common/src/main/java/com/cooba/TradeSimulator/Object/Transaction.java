package com.cooba.TradeSimulator.Object;

public class Transaction<T> {
    private TradeStep<T> tradeStep;

    public Transaction(TradeStep<T> tradeStep) {
        this.tradeStep = tradeStep;
    }

    public void startTransaction(T data) {
        tradeStep.doAction(data);
    }
}
