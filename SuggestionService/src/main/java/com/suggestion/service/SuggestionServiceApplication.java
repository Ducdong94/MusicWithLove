package com.suggestion.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.suggestion.service.model.entities")
public class SuggestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuggestionServiceApplication.class, args);
    }

}
