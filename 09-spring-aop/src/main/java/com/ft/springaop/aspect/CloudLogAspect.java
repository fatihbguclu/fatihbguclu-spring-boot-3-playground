package com.ft.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CloudLogAspect {

    @Before("com.ft.springaop.aspect.AopPointcut.forDaoPackageNoGetterSetter()")
    public void logToCloud() {
        System.out.println("\n=====>>> Logging to Cloud in async fashion");
    }

}
