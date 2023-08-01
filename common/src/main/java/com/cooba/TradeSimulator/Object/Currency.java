package com.cooba.TradeSimulator.Object;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class Currency {
    private String name;
}
