package com.example.springboot.study.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

//아래의 import 3개는 직접 작성
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

/*
 *  A003
 *  자동 임포트 : 해당 부분에서 Alt + Enter
 *
 *  @ExtendWith : Junit4에서 @RunWith 에 해당
 *      스프링 부트 테스트와 Junit 연결 하는 기능을 수행
 *  @WebMvcTest
 *      @Controller, @ControllerAdvice 등을 사용 가능하게
 *      @Service, @Repository 등에서는 사용할 수 없다.
 *  */

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void helloReturnTest() throws Exception {
        String hello = "hello";

        // method chain
        // a().b().c()
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // 200 OK
                .andExpect(content().string(hello));
    }

    /*
     * A010 dto 단위테스트
     *  param() : API 테스트를 할 때 사용된 요청 파라미터 설정
     *  단 String 만 허용되기 때문에 int age 는 String.valueOf(age) 처럼 사용해야만 한다.
     *
     *  jsonPath()
     *      JSON 응답값을 필드별로 검증하는 메소드
     *      $단위로 변수를 구분해주고 앞에 Dot(.)을 써준다. $.name 처럼 사용
     *
     *  단위테스트가 끝나면... 실제 프로그램으로 확인
     *      Application으로 가성 실행해야 한다.
     *      http://localhost:8080/hello/dto?name=이순신&age=34
     * */
    @Test
    public void helloDtoTest() throws Exception {
        String name = "Hong Kil Dong";
        int age = 34;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("age", String.valueOf(age)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.age", is(age)));
    }
}
