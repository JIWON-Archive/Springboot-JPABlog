package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping({"", "/"})
    // main 페이지로 갈때는 인증이 필요없어서 지움
    // public String index(@AuthenticationPrincipal PrincipalDetail principal) {
    public String index(Model model, @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // 컨트롤러에서 세션을 어떻게 찾는 지?
        // /WEB-INF/views/index.jsp
//		System.out.println("로그인 사용자 아이디:"+principal.getUsername());
        model.addAttribute("boards", boardService.글목록(pageable));
        // index라는 페이지로  boards가 날아간다.
        return "index"; // viewResolver가 작동!! model의 정보를 들고 간다. model은 request 정보
    }
    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("board",boardService.글상세보기(id));
        return "board/detail";
    }

    // USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
