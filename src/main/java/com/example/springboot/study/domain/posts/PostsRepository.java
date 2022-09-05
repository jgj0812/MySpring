package com.example.springboot.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * A013 인터페이스
 *
 *      JPA 에서는 Repository 라고 부른다.
 *      JpaRepository<클래스, PK> 상속만하면 기본적으로 CRUD 메소드가 자동으로 생성
 *      Entity 클래스인 Posts 와 Entity Repository 인 postsRepository 는 같은 위치에 있어야 한다.
 *      이 저장소가 만들어지는데 단위테스트를 해보겠습니다.
 *      PostsRepositoryTest : 패키지 구조도 같아야함.
 * */
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // Do Nothing
}
