package com.example.springboot.study.web.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * A007 단위테스트를 위한 코드
 *  단위 테스트를 수행하면 에러 가능성
 *
 *  final 변수인 name, age 가 초기화되지 못해서 에러 발생
 *  gradle 버전 문제라서
 *      gradle.build 파일을 수정해줘야 한다.
 *      롬복사용을 더 구체적으로 명시
 * */
@ExtendWith(SpringExtension.class)

public class HelloResponseDtoTest {
    @Test
    public void lombokTest() {
        String name = "test";
        int age = 123;

        HelloResponseDto dto = new HelloResponseDto(name, age);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAge()).isEqualTo(age);
    }
}
