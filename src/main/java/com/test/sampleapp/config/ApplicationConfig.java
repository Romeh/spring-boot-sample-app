package com.test.sampleapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MRomeh
 */
@Configuration
public class ApplicationConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
