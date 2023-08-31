package com.cooba.TradeSimulator.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RedisAspect {
    @Value("${spring.redisson.enable}")
    private boolean redissonEnable;

    @Around("execution(* com.cooba.TradeSimulator.Util.RedisUtil.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        if (redissonEnable) {
            return joinPoint.proceed();
        }
        return null;
    }
}
