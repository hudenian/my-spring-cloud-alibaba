package com.huma.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author hudenian
 * @date 2021/12/14
 */
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.huma.cloud.mapper"})
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
