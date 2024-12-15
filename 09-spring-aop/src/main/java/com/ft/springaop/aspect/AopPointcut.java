package com.ft.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopPointcut {

    @Pointcut("execution(* com.ft.springaop.repository.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution(* com.ft.springaop.repository.*.get*(..))")
    public void getter() {}

    @Pointcut("execution(* com.ft.springaop.repository.*.set*(..))")
    public void setter() {}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

}
