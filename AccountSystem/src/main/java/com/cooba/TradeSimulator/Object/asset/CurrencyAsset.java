package com.cooba.TradeSimulator.Object.asset;

import com.cooba.TradeSimulator.Object.Asset;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CurrencyAsset extends Asset {
    private Integer currencyId;
    private String currencyName;
}
