package com.cooba.TradeSimulator.Object.asset;

import com.cooba.TradeSimulator.DataLayer.CurrencyDatabase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CurrencyAsset extends Asset {
    private Integer currencyId;
    private String currencyName;

    @Override
    public Asset exchange(Integer currencyId, CurrencyDatabase currencyDatabase) {
        Integer inId = this.currencyId;
        BigDecimal fromRate = currencyDatabase.getRate(inId);

        BigDecimal toRate = currencyDatabase.getRate(currencyId);

        return CurrencyAsset.builder()
                .amount(this.getAmount().multiply(fromRate).divide(toRate, 5, RoundingMode.FLOOR))
                .build();
    }
}
