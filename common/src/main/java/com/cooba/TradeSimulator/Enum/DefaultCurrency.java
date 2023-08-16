package com.cooba.TradeSimulator.Enum;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum DefaultCurrency {
    TWD(1, "TWD", BigDecimal.ONE),
    USD(2, "USD", BigDecimal.valueOf(31)),
    JPY(3, "JPY", BigDecimal.valueOf(0.2131)),
    KRD(4, "USD", BigDecimal.valueOf(0.02273)),
    CNY(5, "USD", BigDecimal.valueOf(4.263)),
    ;

    DefaultCurrency(int id, String name, BigDecimal rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    private final int id;
    private final String name;
    private final BigDecimal rate;
    private final static Map<Integer, BigDecimal> currencyMap = new HashMap<>();
    private final static Map<String, DefaultCurrency> nameMap = new HashMap<>();

    static {
        for (DefaultCurrency value : DefaultCurrency.values()) {
            currencyMap.put(value.id, value.rate);
            nameMap.put(value.name, value);
        }
    }

    public static Map<String, DefaultCurrency> getCurrencyNameMap() {
        return nameMap;
    }

    public static Map<Integer, BigDecimal> getCurrencyMap() {
        return currencyMap;
    }
}
