package com.cooba.TradeSimulator.Util;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

public class RandomUtil {

    public static <T> T randomByWeight(Map<T, Integer> weightMap) {
        Random random = new SecureRandom();
        int rand = random.nextInt(weightMap.values().stream().reduce(0, Integer::sum)) + 1;
        for (Map.Entry<T, Integer> entry : weightMap.entrySet()) {
            rand = rand - entry.getValue();

            if (rand <= 0) return entry.getKey();
        }
        return null;
    }
}
