package com.cooba.TradeSimulator.Util;

import com.cooba.TradeSimulator.Exception.NoLockException;
import com.cooba.TradeSimulator.Object.ThrowableRunnable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimulateMultiThread {
    private final Map<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();
    private final String lockKey;
    private final int waitTime;
    private final int timeout;

    public SimulateMultiThread(String lockKey, int waitTime, int timeout) {
        this.lockKey = lockKey;
        this.waitTime = waitTime;
        this.timeout = timeout;
    }

    private List<CompletableFuture<Result>> completableFutureList;
    private int successTime;
    private int failureTime;

    public void run(int loopTime, Runnable runnable) {
        completableFutureList = IntStream.rangeClosed(1, loopTime)
                .boxed()
                .map(i -> CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    try {
                        runnable.run();
                        long end = System.currentTimeMillis();
                        return new Result(Thread.currentThread().getName(), end - start, true, null);
                    } catch (Exception e) {
                        long end = System.currentTimeMillis();
                        return new Result(Thread.currentThread().getName(), end - start, false, e.getMessage());
                    }
                }))
                .collect(Collectors.toList());
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));
        allFuture.join();
        System.out.println("All completed");

        printStatistics();
    }

    public void runWithLock(int loopTime, ThrowableRunnable runnable) {
        completableFutureList = IntStream.rangeClosed(1, loopTime)
                .boxed()
                .map(i -> CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    Lock lock = getLock(this.lockKey);
                    try {
                        if (lock.tryLock(this.waitTime, TimeUnit.MILLISECONDS)) {
                            try {
                                long releaseTime = System.currentTimeMillis() + timeout;
                                runnable.run();
                                if (System.currentTimeMillis() > releaseTime) {
                                    System.out.println("Warning!! Running time is larger than timeout");
                                }
                            } finally {
                                lock.unlock();
                            }
                        } else {
                            long end = System.currentTimeMillis();
                            return new Result(Thread.currentThread().getName(), end - start, false, new NoLockException().getMessage());
                        }
                    } catch (Exception e) {
                        long end = System.currentTimeMillis();
                        return new Result(Thread.currentThread().getName(), end - start, false, e.getMessage());
                    }

                    long end = System.currentTimeMillis();
                    return new Result(Thread.currentThread().getName(), end - start, true, null);
                }))
                .collect(Collectors.toList());
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]));
        allFuture.join();
        System.out.println("All completed");

        printStatistics();
    }

    private void printStatistics() {
        long successCostTime = 0;
        long failureCostTime = 0;
        for (CompletableFuture<Result> completableFuture : completableFutureList) {
            try {
                Result result = completableFuture.get();
                if (result.success) {
                    successCostTime = successCostTime + result.costTime;
                    successTime++;
                } else {
                    System.out.println(result.threadName + " error message: " + result.errorMessage);
                    failureCostTime = failureCostTime + result.costTime;
                    failureTime++;
                }
            } catch (Exception e) {
                failureTime++;
            }
        }
        long totalCostTime = successCostTime + failureCostTime;
        long avgSuccessCost = successTime == 0 ? 0 : successCostTime / successTime;
        long avgFailureCost = failureTime == 0 ? 0 : failureCostTime / failureTime;
        System.out.println("--------------------------------");
        System.out.println("avgCostTime = " + totalCostTime / completableFutureList.size());
        System.out.println("totalCostTime = " + totalCostTime);
        System.out.println("avgSuccessCost = " + avgSuccessCost);
        System.out.println("success = " + successTime);
        System.out.println("avgFailureCost = " + avgFailureCost);
        System.out.println("failure = " + failureTime);
    }

    public ReentrantLock getLock(String lockKey) {
        lockMap.putIfAbsent(lockKey, new ReentrantLock());
        return lockMap.get(lockKey);
    }

    @Data
    @AllArgsConstructor
    private static class Result {
        private String threadName;
        private long costTime;
        private boolean success;
        private String errorMessage;
    }
}
