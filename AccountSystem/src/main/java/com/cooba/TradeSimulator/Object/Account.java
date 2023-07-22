package com.cooba.TradeSimulator.Object;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
public class Account {
    private Long id;
    private String uuid;
    private String name;
    private String password;
    private Date createdTime;
    private Date updatedTime;
}
