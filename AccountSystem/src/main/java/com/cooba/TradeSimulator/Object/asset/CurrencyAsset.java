package com.cooba.TradeSimulator.Object.asset;

import com.cooba.TradeSimulator.DataLayer.CurrencyData;
import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.cooba.TradeSimulator.Object.Asset;
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
    public Asset exchange(Integer currencyId, CurrencyData currencyData) {
        Integer inId = this.currencyId;
        BigDecimal fromRate = currencyData.getCurrencyRate(inId);

        BigDecimal toRate = currencyData.getCurrencyRate(currencyId);

        return CurrencyAsset.builder()
                .amount(this.getAmount().multiply(fromRate).divide(toRate, 5, RoundingMode.FLOOR))
                .build();
    }
}
