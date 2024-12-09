package com.ft.springaop;

import com.ft.springaop.repository.AccountRepository;
import com.ft.springaop.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class AopApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;

    private final MembershipRepository membershipRepository;

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //demoTheBeforeAdvice();
        demoTheAfterThrowingAdvice();
    }

    private void demoTheBeforeAdvice() {
        Account myAccount = new Account();
        accountRepository.addAccount(myAccount, true);
        accountRepository.doWork();

        accountRepository.setName("foobar");
        accountRepository.setServiceCode("silver");

        String name = accountRepository.getName();
        String code = accountRepository.getServiceCode();

        membershipRepository.addSillyMember();
        membershipRepository.goToSleep();

        accountRepository.findAccounts(false);
    }

    private void demoTheAfterThrowingAdvice() {
        List<Account> accounts = null;
        try {
            accounts = accountRepository.findAccounts(true);
        }
        catch (Exception exc) {
            System.out.println("\n\nMain Program: ... caught exception: " + exc);
        }
        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
        System.out.println("----");
        System.out.println(accounts);
        System.out.println("\n");
    }
}
