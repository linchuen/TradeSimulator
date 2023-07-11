package com.cooba.TradeSimulator.Object;

import com.cooba.TradeSimulator.Entity.StockTradeRecord;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StockTradeRecordReq extends StockTradeRecord implements Request{

}