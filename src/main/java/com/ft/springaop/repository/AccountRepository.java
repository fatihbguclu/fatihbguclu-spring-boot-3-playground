package com.ft.springaop.repository;

import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

    public void addAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

}
