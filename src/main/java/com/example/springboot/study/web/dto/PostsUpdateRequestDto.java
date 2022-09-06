package com.example.springboot.study.web.dto;

/*
 * B006 수정하기 요청을 위한 DTO 객체 만들기
 * 등록하기와 유사
 *
 *      일반적으로 update에는 DB에 쿼리하는 부분이 없다.
 *      이유는 JPA의 영속성(향상성) 컨텍스트 때문이다.
 *      영속성 컨텍스트는 Entity를 영구 저장하는 환경이다.
 *      해당 데이터를 변경하면 트랜잭션이 끝나는 시점에서 해당 테이블에 변경된 내용을 반영한다.
 *      즉, Entity객체의 값만 변경하면 별도로 Update 쿼리를 수행하지 않아도 된다.
 *      이런 개념을 Dirty Checking이라고 한다.
 *
 *      개발 순서 확인
 *      DTO --> Service --> Controller
 *
 *      ResponseDTO
 *
 *      PostsResponseDto.java
 * */

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
