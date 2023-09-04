package com.cooba.TradeSimulator.Enum;

import lombok.Getter;

@Getter
public enum CmdEnum {
    cmd(1),
    createAccount(101),
    updateAccountIfExist(102),
    deleteAccountIfExist(103),
    getAccount(104),
    deposit(105),
    withdraw(106),
    assessAssetByUnit(107),
    getCurrencyInfo(201),
    getTodayStockData(301),
    findAllStockInfo(302),
    findStockInfo(303),
    buy(401),
    sell(402);

    private final int code;

    CmdEnum(int code) {
        this.code = code;
    }
}
