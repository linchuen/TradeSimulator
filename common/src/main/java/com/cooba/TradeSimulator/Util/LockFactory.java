package com.cooba.TradeSimulator.Util;

import com.cooba.TradeSimulator.Enum.LockEnum;
import com.cooba.TradeSimulator.Service.Interface.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

@Component
public class LockFactory {
    @Autowired
    private LockService[] lockServiceList;

    private final Map<LockEnum, LockService> lockMap = new HashMap<>();

    @PostConstruct
    public void init() {
        for (LockService lockService : lockServiceList) {
            lockMap.put(lockService.getLockEnum(), lockService);
        }
    }

    public LockService getFactory(LockEnum lockType) {
        return lockMap.get(lockType);
    }

    public Lock getLock(LockEnum lockType, String key) {
        return lockMap.get(lockType).getLock(key);
    }
}
