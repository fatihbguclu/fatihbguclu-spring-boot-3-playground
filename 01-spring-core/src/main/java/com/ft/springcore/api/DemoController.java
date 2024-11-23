package com.ft.springcore.api;

import com.ft.springcore.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //@Autowired field injection
    private final Coach coach;

    private final Coach anotherCoach;

    @Autowired // no need for this annotation
    public DemoController(@Qualifier("trackCoach") Coach coach, @Qualifier("swimCoach") Coach anotherCoach) {
        this.coach = coach;
        this.anotherCoach = anotherCoach;
    }

    /*
    @Autowired // setter injection
    public void setCoach(Coach coach) {
        this.coach = coach;
    }*/

    @GetMapping("/")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }

    @GetMapping("/scopeCheck")
    public String getScopeCheck() {
        return "Comparing beans: myCoach == anotherCoach, " + (coach == anotherCoach);
    }
}
