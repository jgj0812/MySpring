package com.example.springboot.study.web.dto;

import com.example.springboot.study.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

/*
 * C009
 *      DTO 처리가 끝나면 Repository에서 데이터를 가져와서 DTO로 만들어주는 Service를 수정하러 갑니다.
 *      service.posts.PostsService
 * */
/*
 * C023 날짜 데이터 타입 변경
 * D008 : hit 기능 추가 (주석마다 D008)
 *        이 작업이 끝나면
 *              DTO -> Service -> Controller
 *
 * */
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private int hit;    // D008
    private int rec;    // D017
    private String modifiedDate;
    //private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.hit = entity.getHit(); // D008
        this.rec = entity.getRec(); // D017
        this.modifiedDate = entity.getModifiedDate();
    }
}
