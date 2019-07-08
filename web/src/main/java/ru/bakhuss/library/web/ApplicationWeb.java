package ru.bakhuss.library.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "ru.bakhuss.library.*")
public class ApplicationWeb extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationWeb.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationWeb.class);
    }
}
