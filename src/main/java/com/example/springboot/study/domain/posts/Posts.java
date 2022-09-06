package com.example.springboot.study.domain.posts;

/*
 * A012 게시글 저장을 위한 객체
 * 혼동이 생기지 않도록 posts 처럼 뒤에 s를 일관적으로 붙일 예정
 *
 *  create table posts (
 *      idx         int(10) auto_increment,
 *      title       varchar(100) not null,
 *      content     text not null,
 *      author      varchar(30),
 *      -- time        datetime,
 *
 *      primary key(idx)
 *  );
 *
 *  간단한 게시판 필드 : id, title, author, content 구성
 *  id 는 자동으로 결정되기 때문에 필드로 두지 않아도 무방한 상태
 *  @Entity : JPA 에서 필요한 어노테이션
 *      클래스와 데이터베이스 테이블을 매핑해주는 역할
 *      Posts.java ------> posts DB Table
 *      MyPosts.java -----> my_posts 이름으로 만들어 진다.
 *
 *  @Id : DB 의 키 값 : Primary Key
 *  @GeneratedValue : 키 생성
 *
 *  저장소를 위한 PostsRepository 생성하러 갈 예정
 *  domain.posts.PostsRepository.java 인터페이스를 만들러 간다.
 *
 * */

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 일부러 어노테이션 사용 안함
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /*
     * B008 title, content를 파라미터로 하는 메소드를 정의하면,
     * 이를 가져다 쓰는 PostsResponseDto에서도 이 메소드를 사용할 수 있다.
     * */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
