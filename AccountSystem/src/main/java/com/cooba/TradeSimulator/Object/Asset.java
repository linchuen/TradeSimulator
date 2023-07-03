package com.cooba.TradeSimulator.Object;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Asset {
    private String type;
    private int amount;
}
