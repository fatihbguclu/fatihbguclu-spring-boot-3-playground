package com.ft.springaop;

import com.ft.springaop.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class AopApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        demoTheBeforeAdvice();
    }

    private void demoTheBeforeAdvice() {
        accountRepository.addAccount();
    }
}
