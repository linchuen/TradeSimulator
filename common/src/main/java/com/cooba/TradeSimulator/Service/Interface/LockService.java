package com.cooba.TradeSimulator.Service.Interface;


import com.cooba.TradeSimulator.Enum.LockEnum;

import java.util.concurrent.locks.Lock;

public interface LockService {

    Lock getLock(String key);

    LockEnum getLockEnum();
}
