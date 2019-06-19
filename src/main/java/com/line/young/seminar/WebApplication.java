package com.line.young.seminar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("WEB-----------------------------------------");
        
        SpringApplication.run(WebApplication.class, args);
    }
}

