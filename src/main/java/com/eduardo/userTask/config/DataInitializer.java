package com.eduardo.userTask.config;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CreateFirstAdmin createFirstAdmin;

    @Override
    @NullMarked
    public void run(String... args) throws Exception {
        createFirstAdmin.seed();
    }
}
