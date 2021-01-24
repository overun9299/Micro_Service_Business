package com.soap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by ZhangPY on 2021/1/24
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain: BusinessApp
 */
@SpringBootApplication
@MapperScan("com.soap.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class BusinessApp {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApp.class , args);
    }
}
