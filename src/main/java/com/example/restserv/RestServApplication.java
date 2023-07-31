package com.example.restserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestServApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RestServApplication.class, args);
    }

}
