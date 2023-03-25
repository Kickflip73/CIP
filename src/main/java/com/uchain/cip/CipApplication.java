package com.uchain.cip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableTransactionManagement
@EnableWebMvc//swagger相关
public class CipApplication {

    public static void main(String[] args) {
        SpringApplication.run(CipApplication.class, args);
    }

}
