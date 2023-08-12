package com.cooba.TradeSimulator.Enum;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public enum LockEnum {
    Redis(),
    Reentrant(),
    ;
}
