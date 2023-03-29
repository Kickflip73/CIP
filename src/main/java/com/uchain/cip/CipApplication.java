package com.uchain.cip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication
@EnableTransactionManagement
public class CipApplication {

    public static void main(String[] args) {
        SpringApplication.run(CipApplication.class, args);
    }
}
