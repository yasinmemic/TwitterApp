package com.opthema.twitter.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
public class ApplicationConfig implements WebMvcConfigurer {

    public ApplicationConfig() {
    }

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
