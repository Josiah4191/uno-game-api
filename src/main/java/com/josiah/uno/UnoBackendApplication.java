package com.josiah.uno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnoBackendApplication.class, args);
    }

}

/*
    First milestone: Can the backend create a valid UNO deck?
    Second milestone: Can the backend start a game and deal cards?
 */