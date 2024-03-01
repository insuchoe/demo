package com.demo.demospringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class DemospringdatajpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemospringdatajpaApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        AuditorAware<String> auditorAware = new AuditorAware<>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                Optional<String> randomUUID = Optional.of(UUID.randomUUID().toString());
                return randomUUID;
            }
        };

        return auditorAware;
    }
}
