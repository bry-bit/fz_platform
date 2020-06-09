package com.system.fz_platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.system.mapper.Synchro")
@ComponentScan(basePackages = "com.system.*")
@EnableTransactionManagement
@EnableScheduling
public class FzPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzPlatformApplication.class, args);
    }

}
