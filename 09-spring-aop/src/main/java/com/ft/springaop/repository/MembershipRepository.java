package com.ft.springaop.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipRepository {

    public boolean addSillyMember() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT");
        return true;
    }

    public void goToSleep() {
        System.out.println(getClass() + ": I'm going to sleep now...");
    }
}
