package com.hp.g11n.automation.passolo.ss.score.annotation;

import com.hp.g11n.automation.passolo.ss.score.IChain;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ChainData {
    String id();
    String name();
    int order();
    Class<? extends IChain> chainClass();
    String description() default "";
}
