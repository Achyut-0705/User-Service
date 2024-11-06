package com.e_job_search.User_Service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
