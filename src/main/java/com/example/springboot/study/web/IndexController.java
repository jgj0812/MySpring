package com.example.springboot.study.web;

import com.example.springboot.study.domain.posts.Posts;
import com.example.springboot.study.service.posts.PostsService;
import com.example.springboot.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/*
 * B017 IndexController
 *      이거 만들고, 단위 테스트
 *
 *      http://localhost:8080/
 *
 *      @Controller : 클래스 타입에 적용
 *          이것을 붙이면 해당 클래스를 웹 요청 처리하는 컨트롤러로 사용하게 된다.
 * 
 * D009 hit를 추가. 주석에 D009
 * */
@RequiredArgsConstructor    // C011에서 추가
@Controller
public class IndexController {

    /*
     * C011 에서 서비스의 findAllDesc()의 결과를 index.mustache에서 사용할 수 있는 posts객체로 만들어준다.
     * */
    private final PostsService postsService;

    /*
    @GetMapping("/")
    public String index(Model model) {
        // posts로 만들어주기
        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
    }
    */

    // 파라미터의 size = 2는 한페이지당 보이는 라인수를 의미한다.
    @GetMapping("/")
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 2) Pageable pageable) {
        Page<Posts> pageList = postsService.pageList(pageable);

        // posts로 만들어주기
        //model.addAttribute("posts", postsService.findAllDesc());

        // View가 화면 페이지 정보를 출력하기 위해서 필요한 정보들을 미리 만들어서 보내준다.
        // 이 값들을 #post 변수 이름으로 사용할 예정
        model.addAttribute("posts", pageList);
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

        model.addAttribute("hasPrev", pageList.hasPrevious());
        model.addAttribute("hasNext", pageList.hasNext());
        return "index";
    }

    /*
    // C011 이전 코드
    @GetMapping("/")
    public String index() {
        return "index";
    }*/

    /*
     * C003 /posts/save 등록
     * */
    @GetMapping("/posts/save")
    public String postsSave() { return "posts-save"; }

    /*
     * C013 /posts/show/1 처리
     * */
    @GetMapping("/posts/show/{id}")
    public String postsShow(@PathVariable Long id, Model model) {
        postsService.updateHit(id); // D009 내용보기할 때 hit 값을 증가시킨 후 가져온다.
                                    // index.mustache에 가서 hit 기능 추가
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-show";
    }

    /*
     * C018 /posts/update/1 글 수정하기 화면을 만들어준다.
     *
     * */
    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-update";
    }
}
