package com.jslee.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /*
         @SprungBootApplication annotaion: 스프링 부트의 자동 설정, 스프링Bean 읽기와 생성 모두 자동으로 설정
         이 어노테이션이 있는 위치부터 설정 읽으므로 프로젝트 최상단에 위치해야 함
         */

        /*
         SpringApplication.run : 내장 was 실행
         tomcat을 설치할 필요 X, 스프링 부트로 만들어진 Jar파일(실행 가능한 Java 패키징 파일)로 실행하면 됨
         */
        SpringApplication.run(Application.class, args);
    }
}
