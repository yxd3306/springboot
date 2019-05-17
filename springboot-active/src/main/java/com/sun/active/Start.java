package com.sun.active;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class,args);
    }

}
