package com.cooba.TradeSimulator.Object;

public abstract class TradeStep<T> {
    private TradeStep<T> nextStep;
    private TradeStep<T> preStep;

    public void next(TradeStep<T> node) {
        if (node == null) return;
        this.nextStep = node;
        node.preStep = this;
    }

    public void doAction(T t) {
        try {
            action(t);
        } catch (Exception e) {
            rollback(t);

            if (preStep != null) {
                preStep.rollback(t);
            }
            throw e;
        }

        if (nextStep != null) {
            nextStep.doAction(t);
        }
    }

    public abstract void action(T t);

    public abstract void rollback(T t);
}
