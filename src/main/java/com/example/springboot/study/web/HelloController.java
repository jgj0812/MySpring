package com.example.springboot.study.web;

/*
 * A002
 * RESTful API
 *
 *  http://test.com/main.jsp?name=test&age=12
 *  http://test.com/mian.jsp/user/2
 *  http://test.com/hello
 *
 *  METHOD
 *      GET     : HTTP Header 정보가 날아간다.
 *          http://test.com/login.jsp?id=test&pass=1234
 *          1. 보안상의 심각한 문제 발생
 *          2. 메시지 길이가 정해져있다. (255B)
 *
 *          http://test.com/hello
 *
 *          http://test.com/show.jsp?id=3
 *      POST    : HTTP Body에 정보가 날아간다.
 *
 *      DELETE
 *      PUT
 *
 *  @RestController
 *      JSON(Javascript Object Notation)로 반환하는 컨트롤로 만들때
 *
 *  HTTP Response Code
 *  1xx : Trying
 *          ------------> 100
 *          <------------ 180 Ringing
 *  2xx : OK
 *  3xx : Redirect Error, Temporary Error
 *  4xx : Permanent Error, Client Error
 *          404 Not Found, 403 Forbidden, 405 Method Not Allowed
 *  5xx : Server Error
 *          Gateway Error
 *  6xx : Global Error
 *
 *  Spring boot 장점
 *      1. Class, DB 매핑
 *      2. Database 변경할 때 포팅이 편하다.
 *      3. 단위테스트 @Test를 사용
 *          HelloController.java
 *          HelloControllerTest.java
 * */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello()
    {
        return "hello";
    }

    @GetMapping("test")
    public String test()
    {
        return "테스트를 결과로 준다.";
    }

}
