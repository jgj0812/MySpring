package com.example.springboot.study.web;

/*
 * B005 저장하는 컨트롤러 단위테스트
 *      @LocalServerPort
 *          8080 포트를 단위테스트에 사용할 수 없어서 랜덤 포트를 사용한다.
 *          WebEnvironment.RANDOM_PORT에 의해서 포트가 결정된다.
 *      @WebMvcTest의 경우에는 JPA에서 작동하지 않기 때문에
 *      JPA에서 테스트할 때는 다음 코드처럼
 *          @SpringBootTest와 @TestRestTemplate 객체를 사용한다.
 *
 *      저장은 정상적으로 동작하는 것을 확인했다.
 *      할 일
 *          수정, 조회하기
 *          저장 : PostsSaveRequestDto를 만들어서 사용했다.
 *          수정 : PostsUpdateRequestDto를 만들어서 사용하자.
 * */

import com.example.springboot.study.domain.posts.Posts;
import com.example.springboot.study.domain.posts.PostsRepository;
import com.example.springboot.study.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

        PostsSaveRequestDto requestDto = PostsSaveRequestDto
                                            .builder()
                                                .title(title)
                                                .content(content)
                                                .author("HongKilDong")
                                            .build();

        // http://localhost:8080/api/v1/posts
        String url = "http://localhost:" + port  + "/api/v1/posts";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> list = postsRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(title);
        assertThat(list.get(0).getContent()).isEqualTo(content);
    }
}
