package com.example.spring_0715;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "com.example.spring_0715.day0717_restapi")
public class Spring0715Application {

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    public void printConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        System.out.println("포트번호: " + port);
        System.out.println("애플리케이션 이름: " + appName);
    }

    public static void main(String[] args) {
        SpringApplication.run(Spring0715Application.class, args);
    }

}
