package com.example.springboot.study.service.posts;

/*
 * B003 study.service.posts.PostsService.java
 *
 *  PostsSaveRequestDto 를 파라미터로 Controller 로 부터 수신한 Service 는 DB에 저장(save())한다.
 *  Controller 와 마찬가지로 생성자를 통한 주입을 위해 @RequiredArgConstructor 를 맨 위에 사용한다.
 *
 *  Service 객체한테 저장하라 save() 라는 명령을 수행하는
 *  Controller 코드를 수정하러 가야한다.
 *      postsApiController.java 로 가서 코드 수정
 * */

import com.example.springboot.study.domain.posts.Posts;
import com.example.springboot.study.domain.posts.PostsRepository;
import com.example.springboot.study.web.dto.PostsSaveRequestDto;
import com.example.springboot.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
     * B009 : update() 메소드 추가
     * */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        // repository에 수정하기를 수행
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No Data ... id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
}
