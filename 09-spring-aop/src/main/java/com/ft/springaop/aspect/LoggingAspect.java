package com.ft.springaop.aspect;

import com.ft.springaop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    @Around("execution(* com.ft.springaop.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        long begin = System.currentTimeMillis();
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = "handle exception message";
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @Before("com.ft.springaop.aspect.AopPointcut.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n=====>>> Executing @Before advice on method");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(arg -> {
            System.out.println(arg);
            if (arg instanceof Account account) {
                System.out.println("account name: " + account.getName());
                System.out.println("account level: " + account.getLevel());
            }
        });
    }

    @AfterReturning(pointcut = "execution(* com.ft.springaop.repository.AccountRepository.findAccounts(..))", returning = "accounts")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> accounts) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);
        System.out.println("\n=====>>> result is: " + accounts);
    }

    @AfterThrowing(pointcut = "execution(* com.ft.springaop.repository.AccountRepository.findAccounts(..))", throwing = "ex")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable ex) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);
        System.out.println("\n=====>>> The exception is: " + ex);
    }

}
