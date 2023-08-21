package com.cooba.TradeSimulator.Object.currency;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class Currency {
    private String name;
}
