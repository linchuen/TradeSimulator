package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Util.RandomUtil;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RandomUtilTest {

    private final Map<String, Integer> weightMap = Map.of(
            "w1", 10,
            "w2", 20,
            "w3", 30,
            "w4", 40);

    @Test
    public void randomTest() {
        Map<String, Integer> countMap = new HashMap<>();
        IntStream.rangeClosed(1, 10000).boxed()
                .forEach(i -> countMap.compute(RandomUtil.randomByWeight(weightMap), (k, v) -> v == null ? 1 : v + 1));

        System.out.println(countMap);
        Assertions.assertThat(countMap.get("w1")).isCloseTo(1000, Percentage.withPercentage(10));
        Assertions.assertThat(countMap.get("w2")).isCloseTo(2000, Percentage.withPercentage(20));
        Assertions.assertThat(countMap.get("w3")).isCloseTo(3000, Percentage.withPercentage(30));
        Assertions.assertThat(countMap.get("w4")).isCloseTo(4000, Percentage.withPercentage(40));
    }
}
