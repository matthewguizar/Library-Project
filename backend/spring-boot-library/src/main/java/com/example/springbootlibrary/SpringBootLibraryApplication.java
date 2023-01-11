package com.example.springbootlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.springbootlibrary")
public class SpringBootLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLibraryApplication.class, args);
    }

}
