package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링이 com.cos.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new 하는 것은 아니다.
// 특정 어노테이션이 붙어있는 클래스 파일들을 new해서(IoC) 스프링 컨테이너에 관리해준다.
@RestController
public class BlogCotrollerTest {
	// http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}

}

// com.cos.blog/이하를 스프링이 스캔 
// com.cos.test/TestController를 만들어선 안된다. 스프링이 스캔하지 않는다.
// 우리가 만든 패키지 이하로 만들어야 스프링이 스캔한다.
// com.cos.blog.controller
// com.cos.blog.resource
// com.cos.blog.domain