package com.example.springboot.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * A001
 * @SpringBootApplication
 *  스프링 부트의 자동 설정
 *  스프링 Bean 읽기와 생성 모두 자동으로 설정
 *  이 어노테이션이 있는 위치부터 설정을 읽는다.
 *  프로젝트의 최 상단에 위치!!
 *
 *  main() 에서 프로그램이 시작된다.
 *  내장된(built-in) WAS(Web Application Server)로 Tomcat 을 기본으로 지원
 *
 *  Controller 를 만든다.
 *
 *  File - Settings -Editor - General - Code Completion -> Match Case 해제
 * 
 *  A004
 *      전체 프로그램 한번 실행해보기
 *
 *          웹서버가 동작하고 확인을 위해
 *          http://localhost:8080/hello
 *          했을 때, 화면에 hello 가 나와야 정상
 *
 *      Class 와 DB를 매핑시켜준다.
 *          Class - Getter, Setter 를 자동으로 처리해주는 라이브러리 설치 : Lombok
 *          롬복
 *              Getter, Setter, Default Constructor, toString() 등을 Annotation 으로 자동 생성
 *
 *              Ctrl + Shift + a => plugins 에서 installed 에서 Lombok 확인
 *
 *              File > Settings > Build, Executing, Deployment > Compiler > Annotation Processors
 * */
@SpringBootApplication
public class Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
