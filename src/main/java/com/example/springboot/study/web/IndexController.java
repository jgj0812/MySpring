package com.example.springboot.study.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * B017 IndexController
 *      이거 만들고, 단위 테스트
 *
 *      http://localhost:8080/
 *
 *      @Controller : 클래스 타입에 적용
 *          이것을 붙이면 해당 클래스를 웹 요청 처리하는 컨트롤러로 사용하게 된다.
 * */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /*
     * C003 /posts/save 등록
     * */
    @GetMapping("/posts/save")
    public String postsSave() { return "posts-save"; }
}
