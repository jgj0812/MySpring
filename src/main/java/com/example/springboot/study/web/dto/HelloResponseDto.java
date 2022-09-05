package com.example.springboot.study.web.dto;

/*
 * A006 롬복 확인
 *  @Getter : get method() 자동 실행
 *  @RequiredArgsConstructor
 *      선언된 final field 가 포함된 생성자를 자동생성
 *
 *  DTO : Data Transfer Object
 *  생성된 Getter 를 단위테스트로 확인
 *      test / web/dto/HelloResponseDtoTest.java
 *
 *  localhost:8080/hello
 *  localhost:8080/dto  추가 ---> HelloController.java 에 추가
 * */

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int age;
}
