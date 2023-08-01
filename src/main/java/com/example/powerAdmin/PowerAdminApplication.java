package com.example.powerAdmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.example.powerAdmin.mapper")
@SpringBootApplication
@EnableCaching
public class PowerAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerAdminApplication.class, args);
    }

}
