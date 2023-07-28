package com.cooba.TradeSimulator.Object;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public abstract class Wallet<T extends Asset> {
    private Integer userId;
    private List<T> assets;
}
