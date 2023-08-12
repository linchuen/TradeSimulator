package com.cooba.TradeSimulator.Util;

import com.cooba.TradeSimulator.Enum.LockEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

@Component
public class LockFactory {
    private Map<LockEnum, Lock> lockMap;

    @PostConstruct
    public void init() {
        lockMap = new HashMap<>();
    }

    public Lock getLock(LockEnum lockType) {
        return lockMap.get(lockType);
    }
}
