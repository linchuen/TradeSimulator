package com.cooba.TradeSimulator.Entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class SkipDate {
    private int id;
    private LocalDate skipDate;
    private LocalDateTime createdTime;
}
