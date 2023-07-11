package com.cooba.TradeSimulator.Entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class SkipDate {
    private int id;
    private String reason;
    private LocalDate skipDate;
    private LocalDateTime createdTime;
}
