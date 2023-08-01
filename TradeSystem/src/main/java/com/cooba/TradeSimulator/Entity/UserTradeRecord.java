package com.cooba.TradeSimulator.Entity;

import lombok.Builder;
import lombok.Data;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserTradeRecord {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String billId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer accountId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer stockId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal price;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDate stockDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal amount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer currencyId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String errMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime updatedTime;
}