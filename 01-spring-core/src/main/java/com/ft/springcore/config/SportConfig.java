package com.ft.springcore.config;

import com.ft.springcore.common.Coach;
import com.ft.springcore.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("swimCoach")
    public Coach swimCoach() {
        return new SwimCoach();
    }

}
