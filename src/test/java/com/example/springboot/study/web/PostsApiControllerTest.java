package com.example.springboot.study.web;

/*
 * B005 저장하는 컨트롤러 단위테스트
 * */

import com.example.springboot.study.domain.posts.PostsRepository;
import com.example.springboot.study.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    public void cleanUp() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void postsSaveTest() throws Exception {
        // testtitle, testcontent, testauthor
        String title = "save test title";
        String content = "save test content";
        // String author = "save test author";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                                                .title(title)
                                                                .content(content)
                                                                .author("HongKilDong")
                                                                .build();
    }
}
