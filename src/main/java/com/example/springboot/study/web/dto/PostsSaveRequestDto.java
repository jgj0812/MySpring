package com.example.springboot.study.web.dto;

/*
 * B001 : 게시글을 저장하는 Request DTO
 *
 *      Entity => Posts 객체와 거의 유사한 형태를 가지고 있다.
 *      Entity Class ( Posts ) 를 Request/Response 클래스로 사용해서는 안된다.
 *      Entity 클래스는 DB와 맞닿아있는 핵심 클래스다.
 *      이게 변경되면 여러 클래스에 영향을 미치기 때문에, 구분하는 것이 바람직.
 *      Request/Response 용 DTO 는 View 를 위한 클래스이기 때문에 자주 변경이 필요한 경우가 많다.
 *      View Layer 와 DB Layer 의 역할 분담하는 것이 바람직하다.
 *
 *      Contoller 에서 결과값을 여러 테이블을 조인해야 하는 경우가 빈번하기 때문에
 *      Entity 클래스 만으로 표현하기 어려운 경우가 종종 발생한다.
 *
 *      따라서!!
 *      꼭 Entity Class 와 Controller 에서 사용할 DTO 를 분리하자.
 *
 *      Controller 를 먼저 만들면 어떤 문제가 발생하는지 확인
 *      web.PostsApiController.java
 * */

import com.example.springboot.study.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
