package com.cooba.TradeSimulator.AOP;

import com.cooba.TradeSimulator.Annotation.Key;
import com.cooba.TradeSimulator.Annotation.TransactionLock;
import com.cooba.TradeSimulator.Enum.LockEnum;
import com.cooba.TradeSimulator.Exception.NoLockException;
import com.cooba.TradeSimulator.Util.LockFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Aspect
@Component
public class LockAspect {
    @Autowired
    private LockFactory lockFactory;
    @Value("${spring.redisson.enable}")
    private boolean redissonEnable;

    @Around("@annotation(com.cooba.TradeSimulator.Annotation.TransactionLock)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        TransactionLock transactionLockAnnotation = methodSignature.getMethod().getAnnotation(TransactionLock.class);
        StringBuilder stringBuilder = new StringBuilder(transactionLockAnnotation.value());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Annotation annotation = args.getClass().getAnnotation(Key.class);
            if (annotation == null) continue;

            stringBuilder.append(arg);
        }
        String lockKey = stringBuilder.toString();

        long waitTime = transactionLockAnnotation.waitTime();
        long leaseTime = transactionLockAnnotation.leaseTime();
        Object result;
        boolean isGetLock;
        Lock lock = lockFactory.getLock(redissonEnable ? LockEnum.Redis : LockEnum.Reentrant, lockKey);
        if (lock instanceof RLock) {
            isGetLock = ((RLock) lock).tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
        } else {
            isGetLock = lock.tryLock(waitTime, TimeUnit.MILLISECONDS);
        }
        if (isGetLock) {
            try {
                result = joinPoint.proceed();
            } finally {
                lock.unlock();
            }
        } else {
            throw new NoLockException();
        }
        return result;
    }
}
