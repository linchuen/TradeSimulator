package com.cooba.TradeSimulator.Entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.annotation.Generated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class SkipDate {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String reason;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDate date;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime createdTime;
}