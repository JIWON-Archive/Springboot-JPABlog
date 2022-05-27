package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 스프링에서 기본적으로 @Controller가 붙으면 해당경로 이하의 파일을 리턴한다. 
@Controller
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본 경로 : src/main/resources/static
		// 리턴명 : /home.html
		// 풀경로 : src/main/resources/static/home.html
		return "/home.html";
	}

	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.jpg";
	}

	@GetMapping("/temp/jsp")
	public String tempJsp() {
		return "test";
	}
}
