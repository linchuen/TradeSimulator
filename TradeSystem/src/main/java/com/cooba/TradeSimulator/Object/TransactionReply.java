package com.cooba.TradeSimulator.Object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionReply implements Response {
    private boolean isSuccess;
    private String errorMsg;
}
