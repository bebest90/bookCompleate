package com.example.booktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BooktestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooktestApplication.class, args);
    }

}
