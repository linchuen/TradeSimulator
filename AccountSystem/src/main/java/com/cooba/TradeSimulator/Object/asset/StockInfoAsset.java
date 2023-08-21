package com.cooba.TradeSimulator.Object.asset;

import com.cooba.TradeSimulator.DataLayer.CurrencyData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StockInfoAsset extends Asset {
    private Integer stockId;
    private BigDecimal closingPrice;
    private String stockcode;
    private String name;
    private String industryType;


    @Override
    public Asset exchange(Integer currencyId, CurrencyData currencyData) {
        BigDecimal closingPrice = this.closingPrice;

        BigDecimal toRate = currencyData.getCurrencyRate(currencyId);

        return CurrencyAsset.builder()
                .amount(this.getAmount().multiply(closingPrice).divide(toRate, 5, RoundingMode.FLOOR))
                .build();
    }
}