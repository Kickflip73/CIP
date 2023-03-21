package com.uchain.cip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("/mapper")
@ComponentScan("com.uchain.cip.mapper")
public class CipApplication {

    public static void main(String[] args) {
        SpringApplication.run(CipApplication.class, args);
    }

}
