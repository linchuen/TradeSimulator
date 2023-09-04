package com.cooba.TradeSimulator.Object;

import jodd.net.HttpStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RestResponse<T> implements Response {
    private int code;
    private String msg;
    private T data;
    private String errorMsg;

    @Override
    public boolean isSuccess() {
        return code == HttpStatus.ok().status();
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
