package com.cooba.TradeSimulator.Object;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class AccountDto {
    private String uuid;
    private String name;
    private String password;

    private List<Wallet> wallets;
}
