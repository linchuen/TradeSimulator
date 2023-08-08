package com.cooba.TradeSimulator.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.Generated;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Currency {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal rate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime updatedTime;
}