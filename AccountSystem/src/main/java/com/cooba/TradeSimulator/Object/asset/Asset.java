package com.cooba.TradeSimulator.Object.asset;

import com.cooba.TradeSimulator.DataLayer.CurrencyDatabase;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Data
@SuperBuilder
public abstract class Asset {
    private BigDecimal amount;

    public abstract Asset exchange(Integer currencyId, CurrencyDatabase currencyDatabase);
}
