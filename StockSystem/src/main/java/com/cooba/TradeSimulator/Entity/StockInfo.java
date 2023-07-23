package com.cooba.TradeSimulator.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StockInfo {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String stockcode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String isinCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDate publishDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String marketType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String industryType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime updatedTime;
}