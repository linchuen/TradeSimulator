package com.cooba.TradeSimulator.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SkipDate {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String reason;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDate date;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private LocalDateTime createdTime;
}