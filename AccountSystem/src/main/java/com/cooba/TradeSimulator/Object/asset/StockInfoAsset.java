package com.cooba.TradeSimulator.Object.asset;

import com.cooba.TradeSimulator.Object.Asset;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StockInfoAsset extends Asset {
    private Integer stockId;
    private String stockcode;
    private String name;
    private String isinCode;
    private LocalDate publishDate;
    private String marketType;
    private String industryType;
}