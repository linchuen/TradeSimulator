package com.cooba.TradeSimulator.Util;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class SimulateMultiThread {
    private List<CompletableFuture<Boolean>> completableFutureList;
    private int successTime;
    private int failureTime;

    public void run(int loopTime, Runnable runnable) {
        completableFutureList = IntStream.rangeClosed(1, loopTime)
                .boxed()
                .map(i -> CompletableFuture.supplyAsync(() -> {
                    try {
                        runnable.run();
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }))
                .collect(Collectors.toList());
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));
        allFuture.join();
        System.out.println("All completed");

        printStatistics();
    }

    private void printStatistics() {
        for (CompletableFuture<Boolean> booleanCompletableFuture : completableFutureList) {
            try {
                if (booleanCompletableFuture.get()) {
                    successTime++;
                } else {
                    failureTime++;
                }
            } catch (Exception e) {
                failureTime++;
            }
        }
        System.out.println("successTime = " + successTime);
        System.out.println("failureTime = " + failureTime);
    }
}
