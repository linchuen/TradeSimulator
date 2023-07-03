package com.cooba.TradeSimulator.Object;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Account {
    private Long id;
    private String uuid;
    private String name;
    private String password;
    private Date createdTime;
    private Date updatedTime;
}
