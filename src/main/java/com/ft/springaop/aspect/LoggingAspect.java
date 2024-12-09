package com.ft.springaop.aspect;

import com.ft.springaop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

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
