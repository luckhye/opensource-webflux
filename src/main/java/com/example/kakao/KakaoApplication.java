package com.example.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.example.kakao.repository")
public class KakaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoApplication.class, args);
    }

}
