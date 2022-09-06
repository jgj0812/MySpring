package com.example.springboot.study.web.dto;

import com.example.springboot.study.domain.posts.Posts;
import lombok.Getter;

/*
 * B007 응답을 위한 DTO
 *
 *      간단하게 설계한 게시판 구조
 *          id, title, content, author만 있는 구조이다.
 *          이중 변경 가능한 항목은 title, content만 가능하도록 되어있다.
 *          author는 수정하는 항목이 아니다.
 *          따라서 title, content를 파라미터로 받아서 수정하는 메소드를 하나 만들어 볼 예정이다.
 *          Posts에서 만들어도 된다.
 *
 *          DTO --> Service
 * */
@Getter
public class PostsResponseDto {
    public Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
