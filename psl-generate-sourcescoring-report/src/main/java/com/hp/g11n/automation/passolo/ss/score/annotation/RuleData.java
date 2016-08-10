package com.hp.g11n.automation.passolo.ss.score.annotation;

import com.hp.g11n.automation.passolo.ss.score.IRule;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RuleData {
    String id();
    String name();
    int order();
    Class<? extends IRule> ruleClass();
    String description() default "";
}
