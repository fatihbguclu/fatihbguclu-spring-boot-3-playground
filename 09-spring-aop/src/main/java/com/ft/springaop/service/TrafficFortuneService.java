package com.ft.springaop.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneService {
    public String getFortune(boolean throwEx) {
        if (throwEx) {
            throw new RuntimeException("throw exception from getFortune() method");
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Expect heavy traffic this morning";
    }
}
