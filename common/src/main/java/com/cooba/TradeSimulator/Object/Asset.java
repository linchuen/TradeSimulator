package com.cooba.TradeSimulator.Object;

import com.cooba.TradeSimulator.DataLayer.CurrencyData;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Data
@SuperBuilder
public abstract class Asset {
    private BigDecimal amount;

    public abstract Asset exchange(Integer currencyId,CurrencyData currencyData);
}
