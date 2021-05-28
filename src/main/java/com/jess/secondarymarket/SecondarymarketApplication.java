package com.jess.secondarymarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jess.secondarymarket.dao")
public class SecondarymarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondarymarketApplication.class, args);
    }

}
