package com.cooba.TradeSimulator.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockTradeRecord {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String stockcode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDate date;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal tradingVolume;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal transaction;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal openingPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal highestPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal lowestPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal closingPrice;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal turnover;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime createdTime;
}