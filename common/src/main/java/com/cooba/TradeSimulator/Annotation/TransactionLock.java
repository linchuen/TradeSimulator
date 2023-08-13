package com.cooba.TradeSimulator.Annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransactionLock {
    String value() default "";
    long waitTime() default 10000;
    long leaseTime() default 30000;
}
