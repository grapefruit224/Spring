package com.example.spring_0709.basic;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "com.example.spring_0709.another")
public class Spring0709Application {
//    @Autowired
//    @Qualifier("sendMessage")
//    Message message;

//    @Value("${server.port}")
//    private int port;
//
//    @Value("${spring.application.name}")
//    private String appName;
//
//    @PostConstruct
//    public void printConfig() {
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
//        System.out.println("포트번호: " + port);
//        System.out.println("애플리케이션 이름: " + appName);
//    }

    public static void main(String[] args) {
//        System.out.println(port);
//        System.out.println(appName); // 0, null로 출력됨. 값이 할당되지 않음. spring호출을 아래애서 하기 때문에. 초기 데이터를 만들기도 전에 호출해버리기 때문...
                                        // @PostConstruct 추가해서 모든 의존성 주입이 완료된 후에 호출되도록 하면 해결!
        SpringApplication.run(Spring0709Application.class, args);


    }

}
