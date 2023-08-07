package com.cooba.TradeSimulator.Annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Step {
    String transaction() default "";
    int sort() default 0;
}
