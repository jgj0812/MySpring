package com.example.springboot.study.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

/*
 * A014 단위테스트
 * save() API 를 이용해서 데이터를 집어넣고
 * 바로 findAll() 로 리스트를 가져옵니다.
 * 첫번째 데이터의 제목이 지금 막 집어넣은 값과 같으면 정상 동작했다고 볼 수 있다.
 * 모든 단위 테스트가 끝나면 데이터 모두 삭제 : deleteAll()
 * */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // 테스트 끝난 후에 싹 지워라
    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();    // DELETE FROM posts WHERE id > 0;
    }

    @Test
    public void saveAndLoad() {
        String title = "테스트 제목";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                                    .title(title)
                                    .content(content)
                                    .author("user@test.com")
                                    .build());

        List<Posts> postsList = postsRepository.findAll();  // SELECT * FROM posts;

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
